package com.example.crudpractice.domain.user.service

import com.example.crudpractice.domain.user.dto.UserLoginRequest
import com.example.crudpractice.domain.user.dto.UserLoginResponse
import com.example.crudpractice.domain.user.dto.UserResponse
import com.example.crudpractice.domain.user.dto.UserSignUpRequest

interface UserService {

    fun signUp(userSignUpRequest: UserSignUpRequest): UserResponse
    fun login(userLoginRequest: UserLoginRequest): UserResponse
}