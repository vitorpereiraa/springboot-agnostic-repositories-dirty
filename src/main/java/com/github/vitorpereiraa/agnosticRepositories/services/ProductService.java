package com.github.vitorpereiraa.agnosticRepositories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.vitorpereiraa.agnosticRepositories.domain.Product;
import com.github.vitorpereiraa.agnosticRepositories.dto.ProductDTO;
import com.github.vitorpereiraa.agnosticRepositories.dto.ProductDTOMapper;
import com.github.vitorpereiraa.agnosticRepositories.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDTOMapper productDTOMapper;

    public Iterable<ProductDTO> getProducts() {
        final Iterable<Product> products = productRepository.findAll();
        return productDTOMapper.productsToDTOs(products);
    }

    public ProductDTO create(final ProductDTO productDTO) {
        final Product product = productDTOMapper.DTOToProduct(productDTO);
        final Product persistedProduct = (Product) productRepository.save(product);
        return productDTOMapper.productToDTO(persistedProduct); 
    }
}
