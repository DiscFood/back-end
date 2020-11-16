package br.com.discfood.exceptions

import br.com.discfood.models.product.ProductCategory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class CategoryNotFoundException(
        override val message: String = "Category not found. Available categories: ${ProductCategory.values().toMutableList().joinToString(separator = ", ")}"
) : RuntimeException()