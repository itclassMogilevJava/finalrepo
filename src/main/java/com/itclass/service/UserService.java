package com.itclass.service;

import com.itclass.domain.User;
import com.itclass.dto.LoginDto;

import java.util.Optional;

public interface UserService {

    Optional<User> findByLogin(String login);
    boolean login(String login, String password);
    boolean save(LoginDto loginDto);
}
