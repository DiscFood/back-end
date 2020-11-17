package br.com.discfood.repositories

import br.com.discfood.models.company.Company
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompanyRepository : JpaRepository<Company, Long> {

    fun findByEmail(email: String) : Company?

    fun existsByEmailOrNameIgnoreCase(email: String, name: String) : Boolean

}