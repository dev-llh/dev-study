package com.example.route

import com.example.core.DatabaseFactory
import com.example.core.DatabaseFactory.database
import com.example.dto.User
import com.example.entity.UserEntity
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.ktorm.dsl.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/getAllUsers") {
            try {
                call.respond(UserService.getAllUsers())
            } catch(e:Exception) {
                println("Error: ${e.message}")
            }
        }

        get("/getUser/{id}") {
            try {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest)
                }

                val users = UserService.getUserById(id!!)
                if (users == null) {
                    call.respond(HttpStatusCode.BadRequest)
                }
                call.respond(users!!)
            } catch(e: Exception) {
                println("Error: ${e.message}")
            }
        }

        post("/addUser") {
            try {
                val user = call.receive<User>()
                call.respond(UserService.insertUser(user))

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        post("/addUser2") {
            try {
                val user = call.receive<User>()
                call.respond(UserService.insertUser2(user))

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        post("/updateUser") {
            try {
                val user = call.receive<User>()
                call.respond(UserService.updateUser(user))

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        post("/deleteUser") {
            try {
                val user = call.receive<User>()
                call.respond(UserService.deleteUser(user.id))

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}
