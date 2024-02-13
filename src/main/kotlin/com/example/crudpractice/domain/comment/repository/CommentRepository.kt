package com.example.crudpractice.domain.comment.repository

import com.example.crudpractice.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}