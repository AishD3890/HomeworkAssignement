package com.aishu.homework;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WarehouseApplication implements CommandLineRunner {








    public static void main(String[] args) {

        new SpringApplicationBuilder(WarehouseApplication.class).run(args);



    }


    @Override
    public void run(String... args) throws Exception {

    }
}
