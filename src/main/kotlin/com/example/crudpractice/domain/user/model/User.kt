package com.example.crudpractice.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

class User (
    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "password", nullable = false)
    var password: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}