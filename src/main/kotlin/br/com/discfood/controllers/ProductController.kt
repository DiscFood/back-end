package br.com.discfood.controllers

import br.com.discfood.models.product.Product
import br.com.discfood.models.product.ProductDTO
import br.com.discfood.services.product.ProductService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
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

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(summary = "Create a new product.", security = [SecurityRequirement(name = "bearerAuth")])
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "200", description = "Product created.", content = [Content(mediaType = "application/json")])
            ]
    )
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
                ApiResponse(responseCode = "200", description = "Found the products.")
            ]
    )
    @Operation(summary = "Get a product. Per category or all.")
    fun get(@RequestParam(required = false) category: String?): List<Product> {
        category?.let {
            val productCategory = productService.findCategoryByName(category)
            return productService.getByCategory(productCategory)
        }


        return productService.getAll()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id.")
    @ApiResponses(
            value = [
                ApiResponse(responseCode = "404", description = "Product not found."),
                ApiResponse(responseCode = "200", description = "Product found.")
            ]
    )
    fun get(@PathVariable id: Long): Product {
        return productService.getById(id)
    }

}