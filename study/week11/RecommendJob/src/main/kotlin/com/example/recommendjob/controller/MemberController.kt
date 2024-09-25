package com.example.recommendjob.controller

import com.example.recommendjob.domain.Member
import com.example.recommendjob.repo.MemberRepo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/members")
class MemberController(private val memberRepo: MemberRepo) {

    @GetMapping
    fun getAllMembers(): List<Member> = memberRepo.findAll()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMember(@RequestBody member: Member): Member = memberRepo.save(member)

    @GetMapping("/{id}")
    fun getMemberById(@PathVariable id: Long): Member = memberRepo.findById(id).get()

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMemberById(@PathVariable id: Long) = memberRepo.deleteById(id)

}
