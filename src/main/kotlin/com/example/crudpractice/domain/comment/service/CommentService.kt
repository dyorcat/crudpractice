package com.example.crudpractice.domain.comment.service

import com.example.crudpractice.domain.comment.dto.CommentResponse
import com.example.crudpractice.domain.comment.dto.CreateCommentRequest
import com.example.crudpractice.domain.comment.dto.DeleteCommentRequest
import com.example.crudpractice.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(createCommentRequest: CreateCommentRequest): CommentResponse
    fun updateComment(updateCommentRequest: UpdateCommentRequest): CommentResponse
    fun deleteComment(deleteCommentRequest: DeleteCommentRequest)
}