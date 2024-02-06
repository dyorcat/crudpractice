package com.example.crudpractice.domain.user.dto

data class UserLoginRequest(
    val nickname: String,
    val password: String,
)