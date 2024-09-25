package com.example.recommendjob.controller

import com.example.recommendjob.service.NoticeService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notice")
class NoticeController(private val noticeService: NoticeService) {
}
