package br.com.discfood.controllers

import br.com.discfood.exceptions.CategoryNotFoundException
import br.com.discfood.models.product.Product
import br.com.discfood.models.product.ProductCategory
import br.com.discfood.models.product.ProductDTO
import br.com.discfood.services.product.ProductService
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid


@RestControllerAdvice
@CrossOrigin
@RequestMapping(value = ["/product"])
@Tag(name = "Product", description = "Product Controller")
class ProductController(
        val productService: ProductService
) {

    @PostMapping
    fun save(
            @ModelAttribute @Valid productDTO: ProductDTO,
            @RequestParam("file", required = true) multipartFile: MultipartFile
    ): ResponseEntity<*> {
        productDTO.file = multipartFile
        val product = productService.save(productDTO)
        return ResponseEntity.ok(product)
    }

    @GetMapping
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "404", description = "Category not found."),
                ApiResponse(responseCode = "200", description = "Found the products.", content = [Content(mediaType = "application/json")])
            ]
    )
    fun get(@RequestParam(required = false) category: String?): List<Product> {
        category?.let {
            val productCategory = productService.findCategoryByName(category)
            return productService.getByCategory(productCategory)
        }


        return productService.getAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): Product {
        return productService.getById(id)
    }

}