package br.com.discfood.repositories

import br.com.discfood.models.product.Product
import br.com.discfood.models.product.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository  : JpaRepository<Product, Long> {

    fun findByCategory(category: ProductCategory) : List<Product>

}