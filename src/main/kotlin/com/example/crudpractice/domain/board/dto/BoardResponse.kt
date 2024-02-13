package com.example.crudpractice.domain.board.dto

import com.example.crudpractice.domain.board.model.Board
import java.time.ZonedDateTime

data class BoardResponse(
    val title: String,
    val content: String?,
    val nickname: String,
    val createdAt: ZonedDateTime,
) {

    companion object {
        fun from(board: Board): BoardResponse {
            return BoardResponse(
            title = board.title,
            content = board.content,
            nickname = board.nickname,
            createdAt = board.createdAt,
            )
        }
    }
}
