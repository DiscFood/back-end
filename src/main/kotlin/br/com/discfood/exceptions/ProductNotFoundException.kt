package br.com.discfood.exceptions

import br.com.discfood.models.product.ProductCategory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFoundException(
        override val message: String = "Product not found. Perhaps you should try using a different id?"
) : RuntimeException()