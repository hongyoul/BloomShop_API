package com.example.demo.member.dataInitializer;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;

@Component
@Order(1)
public class MemberDataInitializer implements ApplicationRunner {
	private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // PasswordEncoder 추가

    public MemberDataInitializer(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder; // PasswordEncoder 주입
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memberRepository.save(new Member("user1", passwordEncoder.encode("1234"), "유저1", "010-0000-0000","인천 서구 석남동", "aa@naver.com", "ROLE_USER"));
		memberRepository.save(new Member("user2", passwordEncoder.encode("1234"), "유저2", "010-0000-0000","인천 서구 석남동", "aa@naver.com", "ROLE_USER"));
		memberRepository.save(new Member("user3", passwordEncoder.encode("1234"), "관리자_율", "010-0000-0000","인천 남동구 논현로", "bb@gmail.com", "ROLE_ADMIN"));
		memberRepository.save(new Member("user4", passwordEncoder.encode("1234"), "관리자_다솜", "010-0000-0000","인천 남동구 논현로", "bb@gmail.com", "ROLE_ADMIN"));
		memberRepository.save(new Member("user5", passwordEncoder.encode("1234"), "관리자_재인", "010-0000-0000","인천 남동구 논현로", "bb@gmail.com", "ROLE_ADMIN"));
		
		
	}
}
