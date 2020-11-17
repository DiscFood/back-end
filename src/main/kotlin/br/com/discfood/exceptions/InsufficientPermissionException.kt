package br.com.discfood.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class InsufficientPermissionException(override val message: String = "You don't have enough permission to do it.") : RuntimeException()