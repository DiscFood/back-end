package br.com.discfood.controllers

import br.com.discfood.models.company.Company
import br.com.discfood.services.company.CompanyService
import io.swagger.v3.oas.annotations.Operation
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
    fun save(@RequestBody company : Company) : ResponseEntity<Any> {
        companyService.save(company)
        return ResponseEntity.ok(company)
    }

    @Operation(summary = "Get company by id", security = [SecurityRequirement(name = "bearerAuth")])
    @GetMapping("/{id}")
    fun getById(@PathVariable id : Long): Company {
        return companyService.findById(id)
    }


}