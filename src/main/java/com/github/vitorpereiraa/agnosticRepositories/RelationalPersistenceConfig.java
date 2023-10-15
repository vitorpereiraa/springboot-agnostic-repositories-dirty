package com.github.vitorpereiraa.agnosticRepositories;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Profile("relationalPersistence")
@Configuration
@EnableJpaRepositories
@EnableReactiveMongoRepositories
@EnableAutoConfiguration(exclude = { 
    org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration.class,
    org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration.class
})
public class RelationalPersistenceConfig {}
