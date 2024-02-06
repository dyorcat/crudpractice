package com.example.crudpractice.domain.user.repository

import com.example.crudpractice.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByNickname(nickname: String): Boolean

    fun findByNickname(nickname: String): User?
}