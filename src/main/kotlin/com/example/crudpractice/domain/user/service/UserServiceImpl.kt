package com.example.crudpractice.domain.user.service

import com.example.crudpractice.domain.exception.dto.InvalidCredentialException
import com.example.crudpractice.domain.exception.dto.UniqueAttributeValueAlreadyExistException
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
            throw UniqueAttributeValueAlreadyExistException("nickname", userSignUpRequest.nickname)
        if(userSignUpRequest.passwordConfirm != userSignUpRequest.password) throw InvalidCredentialException()
        val user = User(
            nickname = userSignUpRequest.nickname,
            password = passwordEncoder.encode(userSignUpRequest.password),
            passwordConfirm = passwordEncoder.encode(userSignUpRequest.passwordConfirm)
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