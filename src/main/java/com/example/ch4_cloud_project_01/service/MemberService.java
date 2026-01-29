package com.example.ch4_cloud_project_01.service;

import com.example.ch4_cloud_project_01.dto.MemberRequest;
import com.example.ch4_cloud_project_01.dto.MemberResponse;
import com.example.ch4_cloud_project_01.entity.Member;
import com.example.ch4_cloud_project_01.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long save(MemberRequest request) {
        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti()
        );
        return memberRepository.save(member).getId();
    }

    public MemberResponse findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAge(),
                member.getMbti()
        );
    }
}

