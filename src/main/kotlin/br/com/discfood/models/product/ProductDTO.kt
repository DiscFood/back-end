package br.com.discfood.models.product

import org.hibernate.validator.constraints.Length
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ProductDTO(
        @Length(min = 4, max = 20)
        val name: String,
        @Length(max = 250)
        val description: String,
        var file: MultipartFile,
        val oldPrice: BigDecimal,
        val newPrice: BigDecimal,
        val category: ProductCategory,
        val owner_id: Long,
        val expiresAt: String = ZonedDateTime.now(ZoneOffset.UTC).plusDays(1).format(DateTimeFormatter.ISO_INSTANT)
)