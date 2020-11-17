package br.com.discfood.services.product

import br.com.discfood.context.getAuthenticatedCompany
import br.com.discfood.exceptions.CategoryNotFoundException
import br.com.discfood.exceptions.ProductNotFoundException
import br.com.discfood.models.product.Product
import br.com.discfood.models.product.ProductCategory
import br.com.discfood.models.product.ProductDTO
import br.com.discfood.repositories.ProductRepository
import br.com.discfood.services.company.CompanyService
import br.com.discfood.services.imgur.ImgurService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(
        val productRepository: ProductRepository,
        val imgurService: ImgurService
) {

    @Autowired
    lateinit var companyService: CompanyService

    fun save(productDTO: ProductDTO) : Product {
        val product = convert(productDTO)

        val imgurResponse = imgurService.upload(productDTO.file)
        product.url = imgurResponse.data.link

        productRepository.save(product)

        return product
    }

    fun getAll() : List<Product> {
        return productRepository.findAll()
    }

    fun getByCategory(productCategory: ProductCategory) : List<Product> {
        return productRepository.findByCategory(productCategory)
    }

    fun getById(id : Long) : Product {
        return productRepository.findById(id).takeIf { it.isPresent }?.get() ?: throw ProductNotFoundException()
    }

    fun findCategoryByName(categoryName : String): ProductCategory {
        val categories = ProductCategory.values().toList()
        return categories.firstOrNull { it.name.toUpperCase() == categoryName.toUpperCase() } ?: throw CategoryNotFoundException()
    }


    private fun convert(productDTO: ProductDTO): Product {
        val authenticatedCompany = getAuthenticatedCompany()
        val owner = companyService.findById(authenticatedCompany.id)

        return Product(
                productDTO.name,
                productDTO.description,
                "",
                productDTO.oldPrice,
                productDTO.newPrice,
                productDTO.category,
                owner,
        )
    }


}