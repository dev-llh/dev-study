package com.example.dao

import com.example.data.Store
import javax.persistence.Persistence

class StoreDao {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("KtorStudy", mapOf(
        "hibernate.classLoaders" to listOf(this::class.java.classLoader)
    ))

    fun getAllStore(): List<Store> {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.createQuery("SELECT u FROM User u", Store::class.java).resultList
        } finally {
            em.close()
        }
    }

    fun getStoreById(id: Long): Store? {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.find(Store::class.java, id)
        } finally {
            em.close()
        }
    }
}
