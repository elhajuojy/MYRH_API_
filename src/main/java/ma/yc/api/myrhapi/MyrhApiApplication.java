package ma.yc.api.myrhapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages = {"ma.yc.api"})
public class MyrhApiApplication implements WebMvcConfigurer {

    public static void main(String[] args) {

        SpringApplication.run(MyrhApiApplication.class, args

        );
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("classpath:/static/uploads/");
    }

}
