package com.itclass.controller;

import com.itclass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView loginAttemp(@RequestParam String login,
                                    @RequestParam String password,
                                    HttpSession httpSession) {
        if (userService.login(login, password)) {
            httpSession.setAttribute("userLogin", login);
            return new ModelAndView("redirect:/goods");
        } else {
            ModelAndView modelAndView = new ModelAndView("login");
            modelAndView.addObject("loginError", "y");
            return modelAndView;
        }
    }
}
