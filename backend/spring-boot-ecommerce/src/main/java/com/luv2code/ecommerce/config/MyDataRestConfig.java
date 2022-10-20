package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.ProductCategory;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * @Author Ken Huang
 * @Description 每一個類都可以針對不想要的HTTP方法，把他排除掉
 * @Date 2022/10/20
 * @version 1.0
 */
@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions ={HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.POST};
        // disable HTTP methods for product: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, Product.class,theUnsupportedActions);
        // disable HTTP methods for productCategory: PUT, POST, and DELETE
        entityNoSupportHttpMethod(config, ProductCategory.class,theUnsupportedActions);
    }

    /**
     * 用來簡化重複的程式碼，只要輸入想要排除的HTTPMETHOD陣列，還有想要排除的類，就可以針對API排除掉不想要的HTTP方法
     * @param config
     * @param type
     * @param theUnsupportedActions
     */
    private void entityNoSupportHttpMethod(RepositoryRestConfiguration config,Class<?> type,HttpMethod[] theUnsupportedActions){
        config.getExposureConfiguration()
                .forDomainType(type)
                // Single item
                .withItemExposure(((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions)))
                // Collection
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }
}
