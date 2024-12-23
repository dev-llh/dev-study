package com.example.hexagonal.adapters.persistence.order

import com.example.hexagonal.core.domain.order.Order
import com.example.hexagonal.core.domain.order.OrderItem
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class OrderEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    val id: Long = 0,

    val name: String,
    val totalPrice: Double,

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
    val items: MutableList<OrderItemEntity> = mutableListOf()
) {
    fun toDomain() = Order(
        id = id,
        name = name,
        totalPrice = totalPrice,
        items = items.map { it.toDomain() }
    )

    companion object {
        fun fromDomain(order: Order): OrderEntity {
            val orderEntity = OrderEntity(
                id = order.id,
                name = order.name,
                totalPrice = order.totalPrice
            )

            val items = order.items.map { OrderItemEntity.fromDomain(it, orderEntity) }

            orderEntity.items.addAll(items)

            return orderEntity
        }
    }
}

@Entity
@Table(name = "order_items")
data class OrderItemEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    val price: Double,
    val quantity: Int,

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    val order: OrderEntity? = null
) {
    fun toDomain() = OrderItem(
        id = id,
        name = name,
        price = price,
        quantity = quantity
    )

    companion object {
        fun fromDomain(orderItem: OrderItem, order: OrderEntity) = OrderItemEntity(
            id = orderItem.id,
            name = orderItem.name,
            price = orderItem.price,
            quantity = orderItem.quantity,
            order = order
        )
    }
}
