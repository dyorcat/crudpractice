package com.example.crudpractice.domain.board.model

import com.example.crudpractice.common.BaseTimeEntity
import com.example.crudpractice.domain.comment.model.Comment
import jakarta.persistence.*

@Entity
@Table(name = "Board")
class Board(
    @Column
    var title: String,

    @Column
    var content: String? = null,

    @Column
    val nickname: String,

    @OneToMany(mappedBy = "board")
    val comments: List<Comment> = emptyList(),

    ): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}