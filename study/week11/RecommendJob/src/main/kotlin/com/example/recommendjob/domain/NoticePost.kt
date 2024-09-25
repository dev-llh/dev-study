package com.example.recommendjob.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class NoticePost(

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val title: String,
    val content: String
)
