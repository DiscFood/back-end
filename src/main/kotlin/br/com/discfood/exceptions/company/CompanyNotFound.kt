package br.com.discfood.exceptions.company

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class CompanyNotFound(override val message: String = "The company wasn't found!") : RuntimeException()