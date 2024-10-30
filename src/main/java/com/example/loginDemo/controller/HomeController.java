package com.example.loginDemo.controller;

import com.example.loginDemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/mypage")
    public String myPage(Model model) {
        // 인증된 사용자 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName(); // 이메일 가져오기

            // 이메일에 따라 마이페이지 데이터를 로드
            userService.findByEmail(email).ifPresent(user -> model.addAttribute("user", user));

            // 모델에 사용자 이메일 추가
            model.addAttribute("userEmail", email);
        }
        return "mypage"; // mypage.html로 이동
    }


}
