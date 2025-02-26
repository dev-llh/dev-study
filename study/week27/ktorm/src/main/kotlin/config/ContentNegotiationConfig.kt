package com.example.config

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun Application.configNegotiation() {
    install(ContentNegotiation) {
        json()
    }
}