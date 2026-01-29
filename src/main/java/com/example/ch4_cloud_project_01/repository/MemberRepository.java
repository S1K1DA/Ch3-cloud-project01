package com.example.ch4_cloud_project_01.repository;

import com.example.ch4_cloud_project_01.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
