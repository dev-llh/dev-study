package com.example.route

import com.example.data.User
import com.example.globalObjectMapper
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRoutes() {
    val userService = UserService
    val objectMapper = globalObjectMapper

    route("/user") {
        get("/all") {
            call.respondText(objectMapper.writeValueAsString(userService.getAllUsers()))
        }

        get("gara") {
            call.respondText(objectMapper.writeValueAsString(User(999, "Gara")))
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
                call.respondText(objectMapper.writeValueAsString(user))
            }
        }

        put("/add") {
            val user = call.receive<User>()
            try {
                val newUser = userService.createUser(user)
                call.respond(HttpStatusCode.Created, objectMapper.writeValueAsString(newUser))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid input")
            }
        }

        post("/{id}") {
            val id = call.parameters["id"]!!.toLong()
            val user = call.receive<User>()
            try {
                val updatedUser = userService.updateUser(id, user)
                call.respond(HttpStatusCode.Created, objectMapper.writeValueAsString(updatedUser))
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
