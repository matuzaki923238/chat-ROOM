// ログイン画面の動き
// 1. 「表示」ボタンでパスワードの表示・非表示を切り替える
// 2. ログインボタンが押されたら入力内容をチェックする
// 3. 問題がなければホーム画面へ移動する

const form = document.getElementById("login-form");
const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const emailError = document.getElementById("email-error");
const passwordError = document.getElementById("password-error");
const formError = document.getElementById("form-error");
const toggleButton = document.getElementById("toggle-password");
const submitButton = form.querySelector('button[type="submit"]');

const PASSWORD_MIN_LENGTH = 8;

// --- 1. パスワードの表示切り替え ---
toggleButton.addEventListener("click", () => {
  const isHidden = passwordInput.type === "password";
  passwordInput.type = isHidden ? "text" : "password";
  toggleButton.textContent = isHidden ? "隠す" : "表示";
  toggleButton.setAttribute("aria-label", isHidden ? "パスワードを隠す" : "パスワードを表示");
});

// --- 2. 入力チェック ---
// エラーがあればメッセージを返し、なければ空文字を返す
function checkEmail(value) {
  if (value === "") return "メールアドレスを入力してください";
  // 「文字@文字.文字」の形になっているかだけを確認する
  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)) return "メールアドレスの形式が正しくありません";
  return "";
}

function checkPassword(value) {
  if (value === "") return "パスワードを入力してください";
  if (value.length < PASSWORD_MIN_LENGTH) return `パスワードは${PASSWORD_MIN_LENGTH}文字以上で入力してください`;
  return "";
}

// 入力欄の下にエラーを表示し、枠を赤くする
function showError(input, errorElement, message) {
  errorElement.textContent = message;
  input.classList.toggle("is-invalid", message !== "");
}

// 入力し直したら、その欄のエラーを消す
emailInput.addEventListener("input", () => showError(emailInput, emailError, ""));
passwordInput.addEventListener("input", () => showError(passwordInput, passwordError, ""));

// --- 3. ログインボタンが押されたとき ---
form.addEventListener("submit", (event) => {
  event.preventDefault(); // ページの再読み込みを止める

  const email = emailInput.value.trim();
  const password = passwordInput.value;

  const emailMessage = checkEmail(email);
  const passwordMessage = checkPassword(password);
  showError(emailInput, emailError, emailMessage);
  showError(passwordInput, passwordError, passwordMessage);
  formError.textContent = "";

  if (emailMessage || passwordMessage) {
    // 最初にエラーが出た欄にカーソルを移す
    (emailMessage ? emailInput : passwordInput).focus();
    return;
  }

  submitButton.disabled = true;

  // TODO: サーバーができたら、ここでメールアドレスとパスワードを送って本人確認する。
  //       失敗したら formError に「メールアドレスまたはパスワードが違います」と出し、
  //       ログイン画面のままにする。
  //       今は画面遷移の確認用として、入力チェックが通ればホーム画面へ移動する。
  window.location.href = "home.html";
});
