package com.example.crudpractice.domain.comment.dto

data class UpdateCommentRequest(
    val id: Long?,
    val content: String,
    val password: String,
    val nickname: String,
) {
}
