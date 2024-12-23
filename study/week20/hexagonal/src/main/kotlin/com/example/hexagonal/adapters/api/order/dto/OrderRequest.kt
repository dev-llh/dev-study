package com.example.hexagonal.adapters.api.order.dto

import com.example.hexagonal.core.domain.order.Order
import com.example.hexagonal.core.domain.order.OrderItem

data class OrderRequest(
    val name: String,
    val totalPrice: Double,
    val items: List<OrderItemRequest>
) {
    fun toOrder(): Order = Order(
        name = name,
        items = items.map { it.toOrderItem() },
        totalPrice = totalPrice
    )
}

data class OrderItemRequest(
    val name: String,
    val price: Double,
    val quantity: Int
) {
    fun toOrderItem(): OrderItem = OrderItem(
        name = name,
        price = price,
        quantity = quantity
    )
}
