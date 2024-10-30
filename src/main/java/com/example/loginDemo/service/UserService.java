package com.example.loginDemo.service;

import com.example.loginDemo.domain.User;
import com.example.loginDemo.dto.UserRequestDto;
import com.example.loginDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long save(UserRequestDto dto){
        // UserRequestDto에서 User 엔티티로 변환
        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName()) // 이름 필드 추가
                .password(passwordEncoder.encode(dto.getPassword())) // 비밀번호 암호화
                .build();

        // User 엔티티 저장
        return userRepository.save(user).getId();
    }

    public boolean authenticate(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(password, user.getPassword()); // 비밀번호 확인
        }
        return false; // 사용자가 존재하지 않으면 인증 실패
    }

}
