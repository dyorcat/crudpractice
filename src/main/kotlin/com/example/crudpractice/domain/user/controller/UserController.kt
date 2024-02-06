package com.example.crudpractice.domain.user.controller

import com.example.crudpractice.domain.user.dto.UserLoginRequest
import com.example.crudpractice.domain.user.dto.UserResponse
import com.example.crudpractice.domain.user.dto.UserSignUpRequest
import com.example.crudpractice.domain.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    val userService: UserService,
) {

    fun signUp(@RequestBody @Valid signUpRequest: UserSignUpRequest): ResponseEntity<UserResponse> {
        val user = userService.signUp(signUpRequest)

        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    fun logIn(@RequestBody @Valid loginRequest: UserLoginRequest): ResponseEntity<UserResponse> {
        val result = userService.login(loginRequest)

        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}