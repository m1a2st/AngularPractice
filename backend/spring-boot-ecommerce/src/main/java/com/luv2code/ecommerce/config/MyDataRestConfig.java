package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Country;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import com.luv2code.ecommerce.entity.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @version 1.0
 * @Author Ken Huang
 * @Description 每一個類都可以針對不想要的HTTP方法，把他排除掉
 * @Date 2022/10/20
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @PersistenceContext
    private EntityManager entityManager;

    @Value("${allowed.origins}")
    private String[] theAllowOrigins;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.POST,HttpMethod.PATCH};
        // disable HTTP methods for product: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, Product.class, theUnsupportedActions);
        // disable HTTP methods for productCategory: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, ProductCategory.class, theUnsupportedActions);
        // disable HTTP methods for country: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, Country.class, theUnsupportedActions);
        // disable HTTP methods for country: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, State.class, theUnsupportedActions);

        //call an internal helper method
        exposeIds(config);

        //configure cors mapping
        cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowOrigins);
    }

    /**
     * 用來簡化重複的程式碼，只要輸入想要排除的HTTPMETHOD陣列，還有想要排除的類，就可以針對API排除掉不想要的HTTP方法
     *
     * @param config
     * @param type
     * @param theUnsupportedActions
     */
    private void entityNoSupportHttpMethod(RepositoryRestConfiguration config, Class<?> type, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(type)
                // Single item
                .withItemExposure(((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                // Collection
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    /**
     * 因為 Spring REST Api 本身不會帶自己的id屬性，所以透過entityManager來獲得id這個屬性
     * @param config
     */
    private void exposeIds(RepositoryRestConfiguration config){

        //expose entity ids
        //
        //get a list of all entity class from the entity manager
        Set<EntityType<?>> entities =this.entityManager.getMetamodel().getEntities();
        // create an array of the entity types
        List<Class> entityClasses =new ArrayList<>();
        //get the entity types for the entities
        for(EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());
        }
        //expose the entity ids for the array of entity/domain types
        //new Class[0]代表創建一個Class陣列
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
