package com.example.hexagonal.adapters.persistence.order

import com.example.hexagonal.core.domain.order.Order
import com.example.hexagonal.ports.out.order.OrderRepositoryPort
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Repository
@Primary
class OrderQPersistenceAdapter(
    private val queryFactory: JPAQueryFactory
) : OrderRepositoryPort {

    @Transactional
    override fun save(order: Order): Order {
        val orderEntity = OrderEntity.fromDomain(order)
//        entityManager.persist(orderEntity)
        return orderEntity.toDomain()
    }

    override fun findAll(): List<Order> {
        val qOrder = QOrderEntity.orderEntity
        val orderEntities = queryFactory.selectFrom(qOrder).fetch()
        return orderEntities.map { it.toDomain() }
    }

    override fun findById(id: Long): Order? {
        val qOrder = QOrderEntity.orderEntity
        val orderEntity = queryFactory.selectFrom(qOrder)
            .where(qOrder.id.eq(id))
            .fetchOne()
        return orderEntity?.toDomain()
    }
}
