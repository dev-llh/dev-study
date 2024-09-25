package com.example.recommendjob.repo

import com.example.recommendjob.domain.NoticePost
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoticePostRepo : JpaRepository<NoticePost, Long> {
}
