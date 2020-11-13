package br.com.discfood.context

import br.com.discfood.exceptions.AuthenticationException
import br.com.discfood.models.company.CompanySecurity
import org.springframework.security.core.context.SecurityContextHolder

data class AuthenticatedCompany(
        val id : Long,
        val email: String
)

fun getAuthenticatedCompany(): AuthenticatedCompany {
    val authentication = SecurityContextHolder.getContext().authentication ?: throw AuthenticationException()

    val userDetails = authentication.principal as CompanySecurity

    return AuthenticatedCompany(
            userDetails.id,
            userDetails.username
    )
}