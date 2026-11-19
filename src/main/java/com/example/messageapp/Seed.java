package com.example.messageapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 部品2：ハッシュと初期データ（Railsの has_secure_password と seeds.rb）
// アプリの起動時に1回だけ動き、テスト用の利用者を登録する
@Component
public class Seed implements CommandLineRunner {

    private final UserRepository users;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Seed(UserRepository users) {
        this.users = users;
    }

    @Override
    public void run(String... args) {
        // すでに登録済みなら何もしない（起動するたびに増えないようにする）
        if (users.findByEmail("student1@example.com").isEmpty()) {
            User u = new User();
            u.setName("学生1");
            u.setEmail("student1@example.com");
            // encoder.encode がハッシュ化。"password" という文字はそのまま保存しない
            u.setPasswordDigest(encoder.encode("password"));
            users.save(u);
        }
    }
}
