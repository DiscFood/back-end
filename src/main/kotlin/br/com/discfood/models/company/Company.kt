package br.com.discfood.models.company

import br.com.discfood.models.BaseEntity
import br.com.discfood.models.product.Product
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.Length
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size


@Entity
@AllArgsConstructor
@NoArgsConstructor
class Company(
        @EqualsAndHashCode.Include
        @Pattern(regexp = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
        @Size(min = 5, max = 60)
        val email: String,
        @NotBlank
        @Size(min = 6, max = 12)
        val name: String,
        var password: String,
) : BaseEntity() {

        @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "owner")
        val products : MutableList<Product> = arrayListOf()

}