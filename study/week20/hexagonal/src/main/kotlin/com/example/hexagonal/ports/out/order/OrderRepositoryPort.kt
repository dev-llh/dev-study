package com.example.hexagonal.ports.out.order

import com.example.hexagonal.core.domain.order.Order

interface OrderRepositoryPort {
    fun save(order: Order): Order
    fun findAll(): List<Order>
    fun findById(id: Long): Order?
}
