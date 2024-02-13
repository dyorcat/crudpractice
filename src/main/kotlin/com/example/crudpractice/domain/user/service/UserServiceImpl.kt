package com.example.crudpractice.domain.user.service

import com.example.crudpractice.domain.exception.ModelNotFoundException
import com.example.crudpractice.domain.user.dto.UserLoginRequest
import com.example.crudpractice.domain.user.dto.UserResponse
import com.example.crudpractice.domain.user.dto.UserSignUpRequest
import com.example.crudpractice.domain.user.model.User
import com.example.crudpractice.domain.user.repository.UserRepository
import com.example.crudpractice.infra.swagger.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {
    override fun signUp(userSignUpRequest: UserSignUpRequest): UserResponse {
        if(userRepository.existsByNickname(userSignUpRequest.nickname))
            throw Exception("중복된 닉네임입니다.")
        if(userSignUpRequest.passwordConfirm != userSignUpRequest.password) throw Exception("비밀번호가 일치하지 않습니다. ")
        val user = User(
            nickname = userSignUpRequest.nickname,
            password = passwordEncoder.encode(userSignUpRequest.password),
        )

        val createdUser = userRepository.save(user)

        val token = jwtPlugin.generateAccessToken(
            id = user.id.toString(),
            nickname = user.nickname,
        )

        return UserResponse.fromEntity(createdUser, token)

    }

    override fun login(userLoginRequest: UserLoginRequest): UserResponse {
        val user = userRepository.findByNickname(userLoginRequest.nickname)
            ?: throw Exception("닉네임 또는 패스워드를 확인해주세요.")

        if (user.nickname != userLoginRequest.nickname ||
            !passwordEncoder.matches(userLoginRequest.password, user.password)
            ) { throw Exception("닉네임 또는 패스워드를 확인해주세요.")
        }

        val token = jwtPlugin.generateAccessToken(
            id = user.id.toString(),
            nickname = user.nickname,
        )

        return UserResponse.fromEntity(user, token)

    }
}