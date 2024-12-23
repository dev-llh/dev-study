package com.example.hexagonal.adapters.api.order.dto

import com.example.hexagonal.core.domain.order.Order

data class OrderResponse(
    val orderNum: Long,
    val name: String,
    val totalPrice: Double,
) {
    companion object {
        fun toOrderResponse(order: Order): OrderResponse = OrderResponse(
            orderNum = order.id,
            name = order.name,
            totalPrice = order.totalPrice
        )
    }
}

//data class OrderItemResponse(
//    val name: String,
//    val price: Double,
//    val quantity: Int
//)
