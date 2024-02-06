package com.example.crudpractice.domain.user.dto

import com.example.crudpractice.domain.user.model.User

data class UserResponse(
    val id: Long?,
    val nickname: String,
    val token: String? = null,
) {

    companion object{
        fun fromEntity(user: User, token: String?): UserResponse {
            return UserResponse(
                id = user.id,
                nickname = user.nickname,
                token = token,
            )
        }
    }
}
