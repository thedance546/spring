package com.example.loginDemo.controller;

import com.example.loginDemo.service.UserService;
import com.example.loginDemo.dto.UserRequestDto;
import com.example.loginDemo.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("userRequest", new UserRequestDto()); // 빈 DTO를 모델에 추가
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("userRequest") UserRequestDto dto) {
        userService.save(dto);
        return "redirect:/login";
    }

//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//        model.addAttribute("userResponse", new UserResponseDto());
//        return "login";
//    }
//
//
//    @PostMapping("/login")
//    public String loginUser(@ModelAttribute("userResponse") UserResponseDto dto, Model model) {
//        // 사용자 인증 로직을 추가합니다.
//        if (userService.authenticate(dto.getEmail(), dto.getPassword())) {
//            return "redirect:/loginHome"; // 로그인 성공 시 홈으로 리디렉션
//        } else {
//            model.addAttribute("error", "이메일 또는 비밀번호가 잘못되었습니다."); // 로그인 실패 메시지 추가
//            return "login"; // 로그인 페이지로 다시 이동
//        }
//    }

    @GetMapping("/loginHome")
    public String loginHome(Model model) {
        // 현재 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 사용자 이름 가져오기
        String username = authentication.getName();

        model.addAttribute("username", username);
        return "loginHome";
    }

}
