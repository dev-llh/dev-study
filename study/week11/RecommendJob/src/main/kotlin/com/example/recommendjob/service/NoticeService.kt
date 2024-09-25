package com.example.recommendjob.service

import com.example.recommendjob.domain.NoticePost
import com.example.recommendjob.repo.NoticePostRepo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NoticeService(private val postRepo: NoticePostRepo) {

    @Transactional(readOnly = true)
    fun findAllPosts(): List<NoticePost> = postRepo.findAll()

    @Transactional
    fun savePost(post: NoticePost): NoticePost = postRepo.save(post)

}
