package com.example.messageapp;

import java.util.Optional;

import jakarta.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 部品3：ログインとログアウト（Railsの SessionsController）
@Controller
public class SessionsController {

    private final UserRepository users;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public SessionsController(UserRepository users) {
        this.users = users;
    }

    // ログイン画面を表示する（Railsの #new）
    @GetMapping("/login")
    public String newSession() {
        return "sessions/new";
    }

    // ログインボタンが押されたとき（Railsの #create）
    @PostMapping("/login")
    public String create(@RequestParam String email, @RequestParam String password,
                         HttpSession session, Model model) {
        // 部品1：メールアドレスで利用者を探す
        Optional<User> user = users.findByEmail(email);
        // 部品2：入力されたパスワードと、保存済みのハッシュを比べる
        //        （Railsの user.authenticate(password)）
        if (user.isPresent() && encoder.matches(password, user.get().getPasswordDigest())) {
            // 部品3：成功したら、利用者の番号をセッションに保存する
            //        （Railsの session[:user_id] = user.id）
            session.setAttribute("userId", user.get().getId());
            return "redirect:/";
        }
        // 失敗：セッションには何も保存せず、ログイン画面に戻す。
        // メールアドレスが無い場合もパスワードが違う場合も同じ文を出す
        // （どのメールアドレスが登録済みかを外部の人に教えないため）
        model.addAttribute("alert", "メールアドレスかパスワードが違います");
        model.addAttribute("email", email); // 入力したメールアドレスを残す
        return "sessions/new";
    }

    // ログアウト（Railsの #destroy）。HTMLのフォームはGETとPOSTしか送れないのでPOSTで作る
    @PostMapping("/logout")
    public String destroy(HttpSession session) {
        // セッションを丸ごと消す（利用者の番号も消える）
        session.invalidate();
        return "redirect:/login";
    }
}
