package com.example.route

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.commonRoutes() {

    route("") {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
