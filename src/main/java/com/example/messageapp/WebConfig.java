package com.example.messageapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 部品4：守りをどの画面に効かせるかの設定
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoginInterceptor loginInterceptor;

    public WebConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**") // すべての画面に守りを効かせる
                // ただし次は除外する（Railsの skip_before_action）
                //   /login        … ログイン画面まで守ると「リダイレクトが多すぎる」になる
                //   /css/**       … ログイン画面のデザイン
                //   /error        … エラー画面
                //   /h2-console/** … データベースの中身を見る開発用の画面（ガイドに追加）
                .excludePathPatterns("/login", "/css/**", "/error", "/h2-console/**");
    }
}
