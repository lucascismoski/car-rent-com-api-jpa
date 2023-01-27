package com.lcfl.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class Application {
    static {
        System.setProperty("oracle.net.tns_admin", System.getProperty("user.dir") + File.separator + "wallet");
    }

    public static void main(String[] args) { SpringApplication.run(Application.class, args); }
}




