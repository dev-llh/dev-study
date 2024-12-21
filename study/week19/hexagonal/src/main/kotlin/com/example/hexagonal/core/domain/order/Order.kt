package com.example.hexagonal.core.domain.order

import java.util.*

data class Order (
    val id: Long = 0,
    val name: String,
    val items: List<OrderItem>,
    val totalPrice: Double
)

data class OrderItem (
    val id: Long = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
