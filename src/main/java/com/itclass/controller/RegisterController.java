package com.itclass.controller;

import com.itclass.exception.DisagreeException;
import com.itclass.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @GetMapping("/register")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }



    @ExceptionHandler(DisagreeException.class)
    public ModelAndView handleException(DisagreeException ex, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("agreeError", ex.getMessage());
        return modelAndView;
    }
}
