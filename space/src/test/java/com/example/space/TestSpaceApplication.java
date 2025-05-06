package com.example.space;

import org.springframework.boot.SpringApplication;

public class TestSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpaceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
