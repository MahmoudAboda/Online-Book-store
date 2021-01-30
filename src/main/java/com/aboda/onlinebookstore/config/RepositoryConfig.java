package com.aboda.onlinebookstore.config;

import com.aboda.onlinebookstore.entity.Book;
import com.sun.deploy.security.ValidationState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import javax.persistence.metamodel.Type;
import javax.persistence.EntityManager;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(
            RepositoryRestConfiguration config
    ){
       config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream()
       .map(Type::getJavaType)
       .toArray(Class[]::new)) ;
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedOrigins("http://localhost:4200");
    }

}
