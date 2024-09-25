package com.example.recommendjob.health

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthCheck {

    @GetMapping
    fun healthCheck(): String = "Health OK"

}
