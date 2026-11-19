package com.example.messageapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// ホーム画面（トップ）。ログインに成功すると、ここに移る
// 守り（LoginInterceptor）が先に動くので、未ログインではここに来られない
@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
