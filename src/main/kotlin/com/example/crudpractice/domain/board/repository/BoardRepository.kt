package com.example.crudpractice.domain.board.repository

import com.example.crudpractice.domain.board.model.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
    fun findAllByOrderByCreatedAtDesc(): List<Board>

}