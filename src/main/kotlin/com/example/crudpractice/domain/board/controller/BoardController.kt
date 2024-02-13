package com.example.crudpractice.domain.board.controller

import com.example.crudpractice.domain.board.dto.BoardResponse
import com.example.crudpractice.domain.board.dto.CreateBoardRequest
import com.example.crudpractice.domain.board.dto.UpdateBoardRequest
import com.example.crudpractice.domain.board.service.BoardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/boards")
@RestController
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping()
    fun findAllBoard(@RequestParam nickname: String?,
                    @RequestParam sort: String?,
    ): ResponseEntity<List<BoardResponse>> {
        val boards = boardService.findAll(nickname, sort)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.findAll())
    }

    @GetMapping("/{boardId}")
    fun getBoardById(@PathVariable boardId: Long): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.findById(boardId))
    }

    @PostMapping
    fun createBoard(@RequestBody createBoardRequest: CreateBoardRequest): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.createBoard(createBoardRequest))
    }

    @PutMapping("/{boardId}")
    fun updateBoard(@PathVariable boardId: Long,
                   @RequestBody updateBoardRequest: UpdateBoardRequest
    ): ResponseEntity<BoardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.updateBoard(boardId, updateBoardRequest))
    }

    @DeleteMapping("/{boardId}")
    fun deleteBoard(@PathVariable boardId: Long): ResponseEntity<Unit> {
        boardService.deleteBoard(boardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }



}