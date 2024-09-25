package com.example.recommendjob.service

import com.example.recommendjob.repo.MemberRepo
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepo: MemberRepo) {
}