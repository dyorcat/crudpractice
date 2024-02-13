package com.example.crudpractice.domain.comment.dto

import com.example.crudpractice.domain.board.model.Board
import com.example.crudpractice.domain.comment.model.Comment

class CommentResponse(
    var id: Long?,
    val content: String,
    val nickname: String,
    var board: Board,
) {
    companion object {
        fun from(comment: Comment): CommentResponse {
            return CommentResponse(
                id = comment.id,
                content = comment.content,
                nickname = comment.nickname,
                board = comment.board ?: throw Exception("존재하지 않는 게시글입니다. ")
            )
        }
    }