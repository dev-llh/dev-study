package com.example.data

import javax.persistence.*


@Entity
@Table(name = "tbl_store")
data class Store(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var name: String = "",
    var address: String = ""
)
