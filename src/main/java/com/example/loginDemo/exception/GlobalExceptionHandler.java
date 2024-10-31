package com.example.loginDemo.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmailException(DuplicateEmailException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "register"; //오류 메시지를 표시할 페이지로 이동
    }
}
