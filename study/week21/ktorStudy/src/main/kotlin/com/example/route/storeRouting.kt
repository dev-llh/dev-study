package com.example.route

import com.example.dao.StoreDao
import com.example.data.Store
import com.example.service.StoreService
import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.storeRoutes() {
    val storeService = StoreService(StoreDao())
    val objectMapper = ObjectMapper()

    route("/store") {
        intercept(ApplicationCallPipeline.Setup) {
            val uri = call.request.uri
            println("Setup store uri: $uri")

        }

        get("/all") {
            call.respondText(objectMapper.writeValueAsString(storeService.getAllStores()))
        }

        get("gara") {
            call.respondText(objectMapper.writeValueAsString(Store(999, "Gara", "Gara")))
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@get
            }
            val user = storeService.getStoreById(id)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound, "User not found")
            } else {
                call.respondText(objectMapper.writeValueAsString(user))
            }
        }
    }
}
