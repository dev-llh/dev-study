package com.example.service

import com.example.core.DatabaseFactory
import com.example.core.DatabaseFactory.database
import com.example.dto.User
import com.example.entity.UserEntity
import org.ktorm.dsl.*

object UserService {
    fun getAllUsers(): List<User> {
        return DatabaseFactory.database.from(UserEntity)
            .select()
            .map {
                User(
                    id = it[UserEntity.id]!!,
                    username = it[UserEntity.username]!!
                )
            }
    }

    fun getUserById(id: Int): User? {
        return DatabaseFactory.database.from(UserEntity)
            .select()
            .where(UserEntity.id.eq(id!!))
            .map { row ->
                User(
                    id = row[UserEntity.id]!!,
                    username = row[UserEntity.username]!!
                )
            }
            .firstOrNull()
    }

    fun insertUser(user: User): Boolean {
        val result = DatabaseFactory.database.insert(UserEntity) {
            set(it.id, user.id)
            set(it.username, user.username)
        }

        if (result > 0) {
            return true
        } else {
            return false
        }
    }

    fun insertUser2(user: User): Boolean {
        val id = user.id
        val username = user.username

        val result = DatabaseFactory.database.useConnection { conn ->
            conn.createStatement().executeUpdate("INSERT INTO users (id, username) VALUES ($id, \'$username\');")
        }

        if (result > 0) {
            return true
        } else {
            return false
        }
    }

    fun updateUser(user: User): Boolean {
        val result = DatabaseFactory.database.update(UserEntity) {
            set(it.username, user.username)
            where { it.id eq user.id }
        }

        if (result > 0) {
            return true
        } else {
            return false
        }
    }

    fun deleteUser(id: Int): Boolean {
        val result = DatabaseFactory.database.delete(UserEntity) {
            it.id eq id
        }

        if (result > 0) {
            return true
        } else {
            return false
        }
    }

}