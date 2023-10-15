package com.github.vitorpereiraa.agnosticRepositories;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Profile("documentPersistence")
@Configuration
@EnableMongoRepositories
public class DocumentPersistenceConfig {}
