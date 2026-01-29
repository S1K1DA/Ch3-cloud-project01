package com.example.ch4_cloud_project_01.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private int age;
    private String mbti;
}
