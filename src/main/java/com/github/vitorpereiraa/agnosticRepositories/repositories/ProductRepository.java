package com.github.vitorpereiraa.agnosticRepositories.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ProductRepository<T, ID> extends CrudRepository<T, ID>{
}
