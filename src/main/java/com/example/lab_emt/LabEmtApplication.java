package com.example.lab_emt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableCaching
@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
public class LabEmtApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabEmtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}

//@ComponentScan( { "it.myapplication.controllers" } )
//@EnableCaching
//@SpringBootApplication( exclude = { SecurityAutoConfiguration.class } )
//public class MyWebApplication{
//    public static void main( String[] args ){
//        SpringApplication.run( MyWebApplication.class, args );
//    }
//}