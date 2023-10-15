package com.github.vitorpereiraa.agnosticRepositories.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.github.vitorpereiraa.agnosticRepositories.dto.ProductDTO;
import com.github.vitorpereiraa.agnosticRepositories.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
       final var products = service.getProducts();
       return ResponseEntity.ok().body( products );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
        try {
            final ProductDTO product = service.create(productDTO);
            return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
        } catch( Exception e ) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }
}
