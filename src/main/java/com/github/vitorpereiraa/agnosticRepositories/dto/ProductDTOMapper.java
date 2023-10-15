package com.github.vitorpereiraa.agnosticRepositories.dto;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.github.vitorpereiraa.agnosticRepositories.domain.Product;

@Component
public class ProductDTOMapper {
   
    public ProductDTO productToDTO(final Product product){
        return new ProductDTO(product.getSku(), product.getDesignation(), product.getDescription());
    }

    public Iterable<ProductDTO> productsToDTOs(final Iterable<Product> products){
        return StreamSupport
                .stream(products.spliterator(),false)
                .map(this::productToDTO)
                .collect(Collectors.toList());
    }
    
    public Product DTOToProduct(final ProductDTO dto){
        return new Product(dto.sku(), dto.designation(), dto.description());
    }

}
