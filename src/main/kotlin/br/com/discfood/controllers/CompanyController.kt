package br.com.discfood.controllers

import br.com.discfood.models.company.Company
import br.com.discfood.services.company.CompanyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController()
@CrossOrigin
@RequestMapping(value = ["/company"])
class CompanyController(
        val companyService: CompanyService
) {

    @PostMapping
    fun save(@RequestBody company : Company) : ResponseEntity<Any> {
        companyService.save(company)
        return ResponseEntity.ok(company)
    }

    fun getById() {


    }






}