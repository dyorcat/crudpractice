package com.example.crudpractice.domain.comment.dto

data class DeleteCommentRequest(
    val id: Long?,
    val password: String,
    val nickname: String,
)
