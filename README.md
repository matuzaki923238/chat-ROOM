# メッセージアプリ

チームで制作している、LINEのようなメッセージアプリです。Java（Spring Boot）で作っています。

## 動かし方

**必要なもの:** Java 17以上（JDK）

```bash
./mvnw spring-boot:run
```

起動したら、ブラウザで `http://localhost:8080` を開きます。テスト用の利用者は `student1@example.com` / `password` です。

## フォルダ構成

| 場所 | 内容 |
|---|---|
| `src/main/java/com/example/messageapp/` | Javaのプログラム（ログインの仕組み、画面の処理） |
| `src/main/resources/templates/` | 画面（Thymeleafのテンプレート） |
| `src/main/resources/static/css/` | 見た目（デザイン） |
| `src/main/resources/application.properties` | データベースなどの設定 |
| `pom.xml` | 使う部品（ライブラリ）の一覧 |
| `docs/` | 設計図と解説 |

## 資料

- [画面遷移図](docs/画面遷移図.md)
- [ログイン機能（Java）の解説](docs/ログイン機能（Java）.md)

## 作業の進め方

1. 作業前に最新版を取り込む（Pull）
2. `main` を直接変えず、作業用のブランチを作る
3. 変更をコミットしてプッシュし、プルリクエストを出す
4. メンバーに確認してもらってから `main` に取り込む
