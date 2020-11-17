package br.com.discfood.exceptions.company

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.CONFLICT)
class CompanyAlreadyExistsException(override val message: String = "There's already a company with this email or name.") : RuntimeException()