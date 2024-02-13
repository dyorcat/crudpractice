package com.example.crudpractice.domain.board.service

import com.example.crudpractice.domain.board.dto.BoardResponse
import com.example.crudpractice.domain.board.dto.CreateBoardRequest
import com.example.crudpractice.domain.board.dto.UpdateBoardRequest

interface BoardService {

    fun findAll(): List<BoardResponse>

    fun findById(boardId: Long): BoardResponse?

    fun createBoard(request: CreateBoardRequest): BoardResponse

    fun updateBoard(boarId: Long, request: UpdateBoardRequest): BoardResponse

    fun deleteBoard(boardId: Long)
}