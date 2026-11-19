package com.example.messageapp;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// 部品1：テーブルを検索・保存する窓口
// 中身を書かなくても、メソッド名から検索の処理が自動で作られる
public interface UserRepository extends JpaRepository<User, Long> {

    // Railsの User.find_by(email: ...) に当たる。見つからないときは空の Optional を返す
    Optional<User> findByEmail(String email);
}
