package com.example.messageapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// 部品1：テーブル（Railsの users と User モデル）
// このクラスの形のとおりに users テーブルが作られる
@Entity
@Table(name = "users")
public class User {

    // 利用者の番号。保存するときに自動で 1, 2, 3... と振られる
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 同じメールアドレスは2人登録できない（重複禁止）
    @Column(unique = true)
    private String email;

    // パスワードそのものではなく、ハッシュを保存する列
    private String passwordDigest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }
}
