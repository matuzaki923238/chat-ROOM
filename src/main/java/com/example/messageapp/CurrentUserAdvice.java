package com.example.messageapp;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

// 部品4：ログイン中の利用者（Railsの current_user）
// すべての画面で ${currentUser} として使えるようにする
@ControllerAdvice
public class CurrentUserAdvice {

    private final UserRepository users;

    public CurrentUserAdvice(UserRepository users) {
        this.users = users;
    }

    @ModelAttribute("currentUser")
    public User currentUser(HttpSession session) {
        // セッションに保存した番号から、利用者を取り出す。未ログインなら null
        Object id = session.getAttribute("userId");
        return id == null ? null : users.findById((Long) id).orElse(null);
    }
}
