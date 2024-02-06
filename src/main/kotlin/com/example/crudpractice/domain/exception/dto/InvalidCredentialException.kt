package com.example.crudpractice.domain.exception.dto

data class InvalidCredentialException(
    override val message: String? = "The credential is invalid"
): RuntimeException()
