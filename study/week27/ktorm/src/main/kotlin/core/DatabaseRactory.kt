package com.example.core

import org.ktorm.database.Database

object DatabaseFactory {
    lateinit var database: Database
        private set

    fun init() {
        database = Database.connect(
            url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;",
            driver = "org.h2.Driver",
            user = "sa",
            password = ""
        ).also { println("Database connected!") }

        database.useConnection { conn ->
            conn.createStatement().executeUpdate("CREATE TABLE users (id INT PRIMARY KEY, username VARCHAR(100));")
            conn.createStatement().executeUpdate("INSERT INTO users (id, username) VALUES (1, 'Alice'), (2, 'Bob');")
        }
    }
}