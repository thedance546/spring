package com.example.loginDemo.service;

import com.example.loginDemo.domain.Role;
import com.example.loginDemo.domain.User;
import com.example.loginDemo.dto.UserRequestDto;
import com.example.loginDemo.exception.DuplicateEmailException;
import com.example.loginDemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email); // 이메일로 사용자 조회
    }

    @Transactional
    public Long save(UserRequestDto dto){

        // 이메일 중복 확인
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new DuplicateEmailException("이미 존재하는 이메일입니다.");
        }
        // UserRequestDto에서 User 엔티티로 변환
        User user = User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();

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

    //전체 회원 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

}
