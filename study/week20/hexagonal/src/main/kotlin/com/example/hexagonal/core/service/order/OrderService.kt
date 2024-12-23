package com.example.hexagonal.core.service.order

import com.example.hexagonal.core.domain.order.Order
import com.example.hexagonal.core.domain.order.OrderItem
import com.example.hexagonal.ports.`in`.usecase.order.OrderUseCase
import com.example.hexagonal.ports.out.order.OrderRepositoryPort
import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepositoryPort
) : OrderUseCase
{
    override fun createOrder(order: Order): Order {
        return orderRepository.save(order)
    }

    override fun getAllOrders(): List<Order> {
        return orderRepository.findAll()
    }

    override fun getOrderById(id: Long): Order? {
        return orderRepository.findById(id)
    }

}
