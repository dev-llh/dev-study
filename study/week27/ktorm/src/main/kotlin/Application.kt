package com.example

import com.example.config.configNegotiation
import com.example.config.configureFrameworks
import com.example.core.DatabaseFactory
import com.example.route.configureRouting
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureFrameworks()
    configureRouting()
    configNegotiation()

    DatabaseFactory.init()

}
