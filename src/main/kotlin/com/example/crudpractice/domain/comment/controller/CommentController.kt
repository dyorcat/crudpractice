package com.example.crudpractice.domain.comment.controller

import com.example.crudpractice.domain.comment.dto.CommentResponse
import com.example.crudpractice.domain.comment.dto.CreateCommentRequest
import com.example.crudpractice.domain.comment.dto.DeleteCommentRequest
import com.example.crudpractice.domain.comment.dto.UpdateCommentRequest
import com.example.crudpractice.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping
    fun createComment(
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        val result = commentService.createComment(createCommentRequest)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(result)
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentResponse> {
        val arguments = UpdateCommentRequest(
            id = commentId,
            content = updateCommentRequest.content,
            nickname = updateCommentRequest.nickname,
            password = updateCommentRequest.password,
        )

        val comment = commentService.updateComment(arguments)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(comment)
    }

    @DeleteMapping("/{commentId}")
    fun deleteReply(@PathVariable commentId: Long, @RequestBody deleteCommentRequest: DeleteCommentRequest):
            ResponseEntity<Unit> {
        val arguments = DeleteCommentRequest(
            id = commentId,
            nickname = deleteCommentRequest.nickname,
            password = deleteCommentRequest.password
        )

        commentService.deleteComment(arguments)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }

}