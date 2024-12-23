package com.example.hexagonal.adapters.api.order

import com.example.hexagonal.adapters.api.order.dto.OrderRequest
import com.example.hexagonal.adapters.api.order.dto.OrderResponse
import com.example.hexagonal.ports.`in`.usecase.order.OrderUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/order")
class OrderControllerAdapter (
    private val orderUseCase: OrderUseCase
) {
    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrderRequest): ResponseEntity<OrderResponse> {
        return ResponseEntity.ok(OrderResponse.toOrderResponse(orderUseCase.createOrder(orderRequest.toOrder())))
    }

    @GetMapping
    fun getAllOrders(): ResponseEntity<List<OrderResponse>> {
        return ResponseEntity.ok(orderUseCase.getAllOrders().map { OrderResponse.toOrderResponse(it) })
    }

    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<OrderResponse> {
        return orderUseCase.getOrderById(id)?. let { ResponseEntity.ok(OrderResponse.toOrderResponse(it)) } ?: ResponseEntity.notFound().build()
    }
}
