package com.example.crudpractice.domain.user.dto

data class UserLoginResponse (
    val nickname: String,
    val password: String,
    val passwordConfirm: String,)