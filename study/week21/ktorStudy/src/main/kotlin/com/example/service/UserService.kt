package com.example.service

import com.example.dao.UserDao
import com.example.data.User

class UserService(private val userDao: UserDao) {
    fun getAllUsers(): List<User> = userDao.getAllUsers()

    fun getUserById(id: Long): User? = userDao.getUserById(id)

    fun createUser(user: User): User {
        if (user.name.isBlank()) {
            throw IllegalArgumentException("Name and email must not be blank")
        }
        return userDao.addUser(user)
    }

    fun deleteUser(id: Long): Boolean = userDao.deleteUser(id)
}