package br.com.discfood.models.product

import br.com.discfood.models.BaseEntity
import br.com.discfood.models.company.Company
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.math.BigDecimal
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
@AllArgsConstructor
@NoArgsConstructor
class Product(

        val name: String,
        val description: String,
        var url: String? = null,
        val oldPrice: BigDecimal,
        val newPrice: BigDecimal,
        val category: ProductCategory,

        @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        @JoinColumn(name = "owner_id")
        val owner: Company,


        val expiresAt: String = ZonedDateTime.now(ZoneOffset.UTC).plusDays(1).format(DateTimeFormatter.ISO_INSTANT)

) : BaseEntity() {


}