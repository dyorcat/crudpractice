package com.example.crudpractice.domain.board.service

import com.example.crudpractice.domain.board.dto.BoardResponse
import com.example.crudpractice.domain.board.dto.CreateBoardRequest
import com.example.crudpractice.domain.board.dto.UpdateBoardRequest
import com.example.crudpractice.domain.board.model.Board
import com.example.crudpractice.domain.board.repository.BoardRepository
import com.example.crudpractice.domain.exception.ModelNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BoardServiceImpl(
    private val boardRepository: BoardRepository,
): BoardService
{
    override fun findAll(): List<BoardResponse> {
        val foundBoards = boardRepository.findAllByOrderByCreatedAtDesc()
        return foundBoards.map{BoardResponse.from(it)}
    }


    override fun findById(boardId: Long): BoardResponse? {
        val foundBoard: Board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        return foundBoard.let{BoardResponse.from(it) }
    }

    override fun createBoard(request: CreateBoardRequest): BoardResponse {
        val savedBoard = boardRepository.save(
            Board(title = request.title,
                content = request.content,
                nickname = request.nickname)
        )
        return BoardResponse.from(savedBoard)
    }

    override fun updateBoard(boardId: Long, request: UpdateBoardRequest): BoardResponse {
        val updatedBoard = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        val (title, content) = request

        updatedBoard.title = title
        updatedBoard.content = content
        return BoardResponse.from(updatedBoard)
    }

    override fun deleteBoard(boardId: Long) {
        val board = boardRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("Board", boardId)
        boardRepository.deleteById(boardId)
    }
}