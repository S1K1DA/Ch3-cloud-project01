package com.example.ch4_cloud_project_01.controller;

import com.example.ch4_cloud_project_01.dto.MemberRequest;
import com.example.ch4_cloud_project_01.dto.MemberResponse;
import com.example.ch4_cloud_project_01.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public Long save(@RequestBody MemberRequest request) {
        log.info("[API - LOG] POST /api/members");
        return memberService.save(request);
    }

    @GetMapping("/members/{id}")
    public MemberResponse find(@PathVariable Long id) {
        log.info("[API - LOG] GET /api/members/{}", id);
        return memberService.findById(id);
    }
}

