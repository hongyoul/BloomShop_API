package com.example.demo.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {

    String id; // 아이디

    String password; // 패스워드

    String name; // 이름
    
    String phone; // 연락처
    
    String address; // 주소
    
    String email; // 이메일
    
    String role; // 등급

}
