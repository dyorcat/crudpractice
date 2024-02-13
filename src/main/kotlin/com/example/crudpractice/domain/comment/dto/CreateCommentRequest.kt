package com.example.crudpractice.domain.comment.dto

data class CreateCommentRequest(
    val content: String,
    val nickname: String,
    val password: String,
    val boardId: Long,
)