package com.itclass.service.impl;

import com.itclass.domain.Authority;
import com.itclass.domain.User;
import com.itclass.dto.LoginDto;
import com.itclass.repository.AuthorityRepository;
import com.itclass.repository.UserRepository;
import com.itclass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthorityRepository authorityRepository;

    @Override
    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public boolean save(LoginDto loginDto) {
        if (findByLogin(loginDto.getUsername()).isPresent()) {
            return false;
        }
        String password = encoder.encode(loginDto.getPassword());
        Authority authority;
        if (loginDto.isAdmin()) {
            authority = authorityRepository.findById("ROLE_ADMIN").orElse(null);
        } else {
            authority = authorityRepository.findById("ROLE_USER").orElse(null);
        }
        userRepository.save(User.builder()
                .login(loginDto.getUsername())
                .authorities(Collections.singletonList(authority))
                .password(password)
                .build());
        return true;
    }

    @Override
    public boolean login(String login, String password) {
        User user = findByLogin(login).orElseThrow(()
                -> new UsernameNotFoundException("Wrong login or password"));
        return (encoder.matches(password, user.getPassword()));
    }
}
