package com.github.vitorpereiraa.agnosticRepositories.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.github.vitorpereiraa.agnosticRepositories.domain.Product;

@Profile("documentPersistence")
@Repository
public interface ProductDocumentRepository extends ProductRepository<Product, Long>{
}
