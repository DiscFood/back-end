package br.com.discfood.services.product

import br.com.discfood.models.product.Product
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


    private fun convert(productDTO: ProductDTO): Product {
        val owner = companyService.findById(productDTO.owner_id)

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