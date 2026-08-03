package co.com.ceiba.alquilerbicicletas.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.lang.NonNull;

@Configuration
public class SecurityConfiguration {
    
    private static final String API = "/api/**";
    @Value("${app.security.cors.allowed-origins:http://localhost:4200}")
    private String allowedOrigins;

    private final ContentTypeInterceptor contentTypeInterceptor;

    public SecurityConfiguration(ContentTypeInterceptor contentTypeInterceptor) {
        this.contentTypeInterceptor = contentTypeInterceptor;
    }

    @Bean
    public WebMvcConfigurer corsAndHeadersConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {

                registry.addMapping(API)
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedMethods(
                                "GET",
                                "POST",
                                "PUT",
                                "PATCH",
                                "DELETE")
                        .allowedHeaders(
                                "Content-Type",
                                "Authorization")
                        .allowCredentials(true);
            }

            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {

                registry.addInterceptor(contentTypeInterceptor)
                        .addPathPatterns(API);
            }
        };
    }
}
