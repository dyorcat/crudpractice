package com.example.crudpractice.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class UserSignUpRequest(

    @field:Length(min = 3, message = "닉네임 입력은 필수입니다.")
    val nickname: String,

    @field:Length(min = 4, message = "비밀번호는 4자 이상이여야 합니다.")
    @field:Pattern(regexp = "[A-Za-z0-9]{0,9}", message = "비밀번호는 영문 대소문자, 숫자를 사용하세요.")
    val password: String,
    val passwordConfirm: String,
)
