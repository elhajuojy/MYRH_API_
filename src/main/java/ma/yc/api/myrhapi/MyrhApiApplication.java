package ma.yc.api.myrhapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@RefreshScope
@ComponentScan(basePackages = {"ma.yc.api"})
public class MyrhApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyrhApiApplication.class, args);
    }

}
