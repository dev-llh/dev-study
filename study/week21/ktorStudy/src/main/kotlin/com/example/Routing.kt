package com.example

import com.example.dao.UserDao
import com.example.data.User
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val userDao = UserDao()
    val userService = UserService(userDao)


    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/all") {
            call.respond(userService.getAllUsers())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val user = userService.getUserById(id)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "User not found")
            } else {
                call.respond(user)
            }
        }

        post("/{id}") {
            val user = call.receive<User>()
            try {
                val newUser = userService.createUser(user)
                call.respond(HttpStatusCode.Created, newUser)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid input")
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@delete
            }
            if (userService.deleteUser(id)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, "User not found")
            }
        }
    }
}
