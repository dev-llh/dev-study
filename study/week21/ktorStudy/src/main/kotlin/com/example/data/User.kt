package com.example.data

import javax.persistence.*


@Entity
@Table(name = "tbl_user")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = ""
)
