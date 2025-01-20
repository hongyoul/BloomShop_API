package com.example.demo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.entity.Member;

// JpaRepository 상속받고, 제네릭 타입 설정
public interface MemberRepository extends JpaRepository<Member, String> {
}
