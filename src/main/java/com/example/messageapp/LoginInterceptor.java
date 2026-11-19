package com.example.messageapp;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// 部品4：守り（Railsの before_action :require_login）
// すべての画面を表示する前に、ここが先に動く
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
            throws IOException {
        // セッションに利用者の番号がなければ、未ログインなのでログイン画面へ移す
        if (req.getSession().getAttribute("userId") == null) {
            res.sendRedirect("/login");
            return false; // false を返すと、本来の画面の処理には進まない
        }
        return true; // ログイン済みなら、そのまま本来の画面へ進む
    }
}
