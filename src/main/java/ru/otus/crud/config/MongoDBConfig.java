package ru.otus.crud.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ru.otus.crud.dao")
public class MongoDBConfig {

    Logger logger = LoggerFactory.getLogger("Mongo");

    @Bean
    public MongoClient mongo() {
        var host = System.getenv("HOST");
        var user = System.getenv("DB_USER");
        var pass = System.getenv("DB_PASS");
        var db = System.getenv("DB_NAME");
        logger.error("{} {} {} {}", host, user, pass, db);
        var creds = MongoCredential.createCredential(user, db, pass.toCharArray());
        var opts = MongoClientOptions.builder().build();
        var serv = new ServerAddress(host);
        return new MongoClient(serv, creds, opts);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), System.getenv("DB_NAME"));
    }
}