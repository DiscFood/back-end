package br.com.discfood.services.company

import at.favre.lib.crypto.bcrypt.BCrypt
import br.com.discfood.exceptions.company.CompanyNotFound
import br.com.discfood.models.company.Company
import br.com.discfood.models.company.CompanyDTO
import org.springframework.stereotype.Service
import br.com.discfood.repositories.CompanyRepository

@Service
class CompanyService(val companyRepository: CompanyRepository) {

    fun save(company : Company) {
        company.password = BCrypt.withDefaults().hashToString(12, company.password.toCharArray())
        companyRepository.save(company)
    }

    fun existsByEmailOrName(email: String, name : String): Boolean {
        return companyRepository.existsByEmailOrNameIgnoreCase(email, name)
    }

    fun findByEmail(email : String) : Company {
        return companyRepository.findByEmail(email) ?: throw CompanyNotFound()
    }

    fun findById(id : Long) : Company {
        return companyRepository.findById(id).takeIf { it.isPresent }?.get() ?: throw CompanyNotFound()
    }

    fun findAll(): List<CompanyDTO> {
        return companyRepository.findAll().map { CompanyDTO(it.id, it.email) }
    }


}