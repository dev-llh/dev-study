package com.example

import com.example.route.commonRoutes
import com.example.route.storeRoutes
import com.example.route.userRoutes
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    intercept(ApplicationCallPipeline.Setup) {
        val uri = call.request.uri
        println("Setup uri: $uri")

    }

    routing {
        commonRoutes()
        storeRoutes()
        userRoutes()
    }
}


val globalObjectMapper = ObjectMapper()


