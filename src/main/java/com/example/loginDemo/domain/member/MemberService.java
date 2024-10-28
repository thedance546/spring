package com.example.loginDemo.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 가입 메서드
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    // 모든 회원 조회 메서드
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    // ID로 회원 조회 메서드
    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }

    // loginId로 회원 조회 메서드
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    // 회원 삭제 메서드
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
