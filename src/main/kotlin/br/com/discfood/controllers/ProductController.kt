package br.com.discfood.controllers

import br.com.discfood.models.product.ProductDTO
import br.com.discfood.services.product.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid


@RestController
@CrossOrigin
@RequestMapping(value = ["/product"])
class ProductController(
        val productService : ProductService
){

    @PostMapping
    fun save(
            @ModelAttribute @Valid productDTO: ProductDTO,
            @RequestParam("file", required = true) multipartFile: MultipartFile
    ): ResponseEntity<*> {
        productDTO.file = multipartFile
        val product = productService.save(productDTO)
        return ResponseEntity.ok(product)
    }

}