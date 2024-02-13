package com.example.crudpractice.domain.comment.service

import com.example.crudpractice.domain.board.repository.BoardRepository
import com.example.crudpractice.domain.comment.dto.CommentResponse
import com.example.crudpractice.domain.comment.dto.CreateCommentRequest
import com.example.crudpractice.domain.comment.dto.DeleteCommentRequest
import com.example.crudpractice.domain.comment.dto.UpdateCommentRequest
import com.example.crudpractice.domain.comment.model.Comment
import com.example.crudpractice.domain.comment.repository.CommentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val boardRepository: BoardRepository,
    ): CommentService {
    override fun createComment(createCommentRequest: CreateCommentRequest): CommentResponse {
        val targetBoard = boardRepository.findByIdOrNull(createCommentRequest.boardId)
        ?: throw Exception("존재하지 않는 게시글입니다. ")

        val comment = Comment(
            content = createCommentRequest.content,
            nickname = createCommentRequest.nickname,
            password = createCommentRequest.password,
            board = targetBoard,
        )
        val result = commentRepository.save(comment)
        return CommentResponse.from(result)
    }

    override fun updateComment(updateCommentRequest: UpdateCommentRequest): CommentResponse {
        val foundComment: Comment = updateCommentRequest.id?.let {
            commentRepository.findByIdOrNull(it)
        } ?: throw Exception("존재하지 않는 댓글입니다. ")

        if (foundComment.password != updateCommentRequest.password || foundComment.nickname != updateCommentRequest.nickname) {
            throw Exception("해당 댓글의 비밀번호와 일치하지 않습니다. ")
        }
        foundComment.checkAuthentication(updateCommentRequest.nickname, updateCommentRequest.password)

        foundComment.changeContent(updateCommentRequest.content)
        commentRepository.save(foundComment)
        return CommentResponse.from(foundComment)
    }

    override fun deleteComment(deleteCommentRequest: DeleteCommentRequest) {
        deleteCommentRequest.id?.let {
            commentRepository.deleteById(it)
            val foundComment = deleteCommentRequest.id?.let {
                commentRepository.findByIdOrNull(it) } ?: throw Exception("존재하지 않는 댓글입니다.")

            if (foundComment.password != deleteCommentRequest.password || foundComment.nickname != deleteCommentRequest.nickname) {
                throw Exception("해당 댓글의 비밀번호와 일치하지 않습니다. ")
            }

            foundComment.checkAuthentication(deleteCommentRequest.nickname, deleteCommentRequest.password)

            commentRepository.deleteById(deleteCommentRequest.id)
        }
    }

}