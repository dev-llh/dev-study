package com.example.recommendjob.repo

import com.example.recommendjob.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepo : JpaRepository<Member, Long> {
}