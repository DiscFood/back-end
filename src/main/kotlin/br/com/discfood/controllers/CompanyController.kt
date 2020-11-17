package br.com.discfood.controllers

import br.com.discfood.exceptions.company.CompanyAlreadyExistsException
import br.com.discfood.models.company.Company
import br.com.discfood.models.company.CompanyDTO
import br.com.discfood.services.company.CompanyService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestControllerAdvice
@CrossOrigin
@RequestMapping(value = ["/company"])
@Tag(name = "Company", description = "Company Controller")
class CompanyController(
        val companyService: CompanyService
) {

    @PostMapping
    @Operation(summary = "Create a new company")
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "409", description = "Conflict error. There's a company with this name or email."),
                ApiResponse(responseCode = "200", description = "Company created!")
            ]
    )
    fun save(@RequestBody company : Company) : ResponseEntity<Any> {
        if (companyService.existsByEmailOrName(company.email, company.name)) {
            throw CompanyAlreadyExistsException()
        }

        companyService.save(company)
        return ResponseEntity.ok(company)
    }

    @GetMapping
    @Operation(summary = "Get all companies")
    fun getAll(): List<CompanyDTO> {
        return companyService.findAll()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get company by id")
    fun getById(@PathVariable id : Long): CompanyDTO {
        val company = companyService.findById(id)
        return CompanyDTO(company.id, company.email)
    }


}