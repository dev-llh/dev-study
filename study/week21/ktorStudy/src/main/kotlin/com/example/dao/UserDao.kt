package com.example.dao

import com.example.data.User
import javax.persistence.Persistence

object UserDao {

    private val entityManagerFactory = Persistence.createEntityManagerFactory("KtorStudy")

    fun getAllUsers(): List<User> {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.createQuery("SELECT u FROM User u", User::class.java).resultList
        } finally {
            em.close()
        }
    }

    fun getUserById(id: Long): User? {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.find(User::class.java, id)
        } finally {
            em.close()
        }
    }

    fun addUser(user: User): User {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.transaction.begin()
            em.persist(user)
            em.transaction.commit()
            user
        } catch (e: Exception) {
            em.transaction.rollback()
            throw e
        } finally {
            em.close()
        }
    }

    fun deleteUser(id: Long) : Boolean {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.transaction.begin()
            val user = em.find(User::class.java, id)
            if (user != null) {
                em.remove(user)
                em.transaction.commit()
                true
            } else {
                em.transaction.rollback()
                false
            }
        } catch (e: Exception) {
            em.transaction.rollback()
            throw e
        } finally {
            em.close()
        }
    }

    fun updateUser(id: Long, user: User) : Boolean {
        val em = entityManagerFactory.createEntityManager()
        return try {
            em.transaction.begin()
            val existingUser = em.find(User::class.java, id)
            if (existingUser != null && id == user.id) {
                existingUser.name = user.name
                em.merge(existingUser)
                em.transaction.commit()
                true
            } else {
                em.transaction.rollback()
                false
            }
        } catch (e: Exception) {
            em.transaction.rollback()
            throw e
        } finally {
            em.close()
        }
    }
}
