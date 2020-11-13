package br.com.discfood.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


@ResponseStatus(HttpStatus.UNAUTHORIZED)
class AuthenticationException(
        override val message: String = "An error happened to your authentication."
) : RuntimeException()