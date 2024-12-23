package com.example.hexagonal.adapters.persistence.order

import com.example.hexagonal.core.domain.order.Order
import com.example.hexagonal.ports.out.order.OrderRepositoryPort
import jakarta.persistence.*
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface JpaOrderRepository : JpaRepository<OrderEntity, Long>

@Repository
//@Primary
class OrderPersistenceAdapter(
    private val jpaOrderRepository: JpaOrderRepository
) : OrderRepositoryPort {
    override fun save(order: Order): Order {
        val orderEntity = OrderEntity.fromDomain(order)
        return jpaOrderRepository.save(orderEntity).toDomain()
    }

    override fun findAll(): List<Order> {
        return jpaOrderRepository.findAll().map { it.toDomain() }
    }

    override fun findById(id: Long): Order? {
        return jpaOrderRepository.findById(id).map { it.toDomain() }.orElse(null)
    }
}

