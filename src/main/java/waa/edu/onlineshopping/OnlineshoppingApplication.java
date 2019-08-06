package waa.edu.onlineshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan({ "waa.edu.onlineshopping.controller","waa.edu.onlineshopping.config" })
public class OnlineshoppingApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineshoppingApplication.class, args);
    }

}
