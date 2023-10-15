# Example of Spring Application that can persist data in both relational and document databases

## Configure Application to work with Document DB (MongoDB)

Make sure these options in application.properties are uncommented:

```
spring.profiles.active=documentPersistence
spring.data.mongodb.uri=${DATASOURCE_URL}
spring.data.mongodb.auto-index-creation: true
spring.data.jpa.repositories.enabled=false
```

Make sure these options are commented in application.properties:

```
# spring.profiles.active=relationalPersistence
# spring.datasource.url=${DATASOURCE_URL:jdbc:h2:file:./data/acme;MV_STORE=FALSE;AUTO_SERVER=true;}
# spring.datasource.username=${DATASOURCE_USER:sa}
# spring.datasource.password=${DATASOURCE_PASSWORD:gg}
# spring.datasource.driverClassName=${DATASOURCE_DRIVER:org.h2.Driver}
# spring.jpa.database-platform=${DATASOURCE_DIALECT:org.hibernate.dialect.H2Dialect}
# spring.jpa.hibernate.ddl-auto=update
# spring.autoconfigure.exclude= \
#   org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
#   org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
```

Make sure docker-compose.yml mongodb services are uncommented:

```
  database:
    container_name: ${MONGODB_HOST}
    image: "mongo:7.0.2"
    restart: always
    ports:
      - "${MONGODB_PORT}:${MONGODB_PORT}"
    environment:
      MONGO_INITDB_ROOT_USERNAME: ${DATASOURCE_USER}
      MONGO_INITDB_ROOT_PASSWORD: ${DATASOURCE_PASSWORD}
      MONGO_INITDB_DATABASE: ${MONGODB_DB_NAME}

  mongo-express:
    image: "mongo-express"
    restart: always
    ports:
      - 8081:8081
    environment:
      ME_CONFIG_MONGODB_ADMINUSERNAME: ${DATASOURCE_USER}
      ME_CONFIG_MONGODB_ADMINPASSWORD: ${DATASOURCE_PASSWORD}
      ME_CONFIG_MONGODB_URL: ${MONGODB_CONNECTION_STRING}
```

Ultimately, make sure these fields are set like this in .env:

```
# Application with PostgreSQL
# DATASOURCE_URL=${POSTGRES_CONNECTION_STRING}
# DATASOURCE_DRIVER=org.postgresql.Driver
# DATASOURCE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

# Application with MongoDB
DATASOURCE_URL=${MONGODB_CONNECTION_STRING}
```

Now, you can just run:

```
make up
```

OR if you don't have make installed

```
docker compose up --build
```

## Configure Application to work with Relational DB (PostgreSQL)

Make sure these options in application.properties are uncommented:

```
# spring.profiles.active=relationalPersistence
# spring.datasource.url=${DATASOURCE_URL:jdbc:h2:file:./data/acme;MV_STORE=FALSE;AUTO_SERVER=true;}
# spring.datasource.username=${DATASOURCE_USER:sa}
# spring.datasource.password=${DATASOURCE_PASSWORD:gg}
# spring.datasource.driverClassName=${DATASOURCE_DRIVER:org.h2.Driver}
# spring.jpa.database-platform=${DATASOURCE_DIALECT:org.hibernate.dialect.H2Dialect}
# spring.jpa.hibernate.ddl-auto=update
# spring.autoconfigure.exclude= \
#   org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\
#   org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
```

Make sure these options are commented in application.properties:

```
spring.profiles.active=documentPersistence
spring.data.mongodb.uri=${DATASOURCE_URL}
spring.data.mongodb.auto-index-creation: true
spring.data.jpa.repositories.enabled=false
```

Make sure docker-compose.yml mongodb services are uncommented:

```
   database:
     container_name: ${POSTGRES_HOST}
     image: "postgres:15.2"
     ports:
       - "${POSTGRES_PORT}:${POSTGRES_PORT}"
     environment:
       - "POSTGRES_DB=${POSTGRES_DB_NAME}"
       - "POSTGRES_USER=${DATASOURCE_USER}"
       - "POSTGRES_PASSWORD=${DATASOURCE_PASSWORD}"

   pgadmin:
     image: dpage/pgadmin4
     container_name: pgadmin4_container
     restart: always
     ports:
       - "8888:80"
     environment:
       PGADMIN_DEFAULT_EMAIL: pg@mail.com
       PGADMIN_DEFAULT_PASSWORD: password
```

Ultimately, make sure these fields are set like this in .env:

```
# Application with PostgreSQL
DATASOURCE_URL=${POSTGRES_CONNECTION_STRING}
DATASOURCE_DRIVER=org.postgresql.Driver
DATASOURCE_DIALECT=org.hibernate.dialect.PostgreSQLDialect

# Application with MongoDB
# DATASOURCE_URL=${MONGODB_CONNECTION_STRING}
```

Now, you can just run:

```
make up
```

OR if you don't have make installed

```
docker compose up --build
```
