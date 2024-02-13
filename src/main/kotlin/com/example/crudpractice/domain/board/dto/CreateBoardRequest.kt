package com.example.crudpractice.domain.board.dto

import com.example.crudpractice.domain.board.model.Board

data class CreateBoardRequest(
    val title: String,
    val content: String?,
    val nickname: String,
) {
    fun to(): Board {
        return Board(
            title = title,
            content = content,
            nickname = nickname,
        )
    }
}
