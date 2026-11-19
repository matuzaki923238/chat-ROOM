package com.example.messageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// アプリの入口。ここから起動すると、同じパッケージ内の部品が自動で読み込まれる
@SpringBootApplication
public class MessageAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageAppApplication.class, args);
    }
}
