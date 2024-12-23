package com.example.hexagonal.ports.`in`.usecase.order

import com.example.hexagonal.core.domain.order.Order

interface OrderUseCase {

    fun createOrder(order: Order): Order
    fun getAllOrders(): List<Order>
    fun getOrderById(id: Long): Order?

}
