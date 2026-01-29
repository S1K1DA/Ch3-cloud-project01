package com.example.ch4_cloud_project_01.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private int age;
    private String mbti;
}
