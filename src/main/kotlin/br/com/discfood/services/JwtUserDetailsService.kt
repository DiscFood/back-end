package br.com.discfood.services

import br.com.discfood.models.company.CompanySecurity
import br.com.discfood.services.company.CompanyService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(private val companyService: CompanyService) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): CompanySecurity {
        val company = companyService.findByEmail(email)

        if (email == company.email) {

            return CompanySecurity(
                    company.id,
                    email,
                    company.password,
                    arrayListOf()
            )

        } else {
            throw UsernameNotFoundException("User not found with username: $email")
        }
    }
}
