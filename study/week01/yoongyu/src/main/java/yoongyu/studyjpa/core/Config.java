package yoongyu.studyjpa.core;

import jakarta.persistence.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @PersistenceUnit
    public EntityManagerFactory entityManagerFactory;

    @PersistenceContext
    public EntityManager entityManager;
}
