# FirebaseEmulatorSuite

Firebase の Emulator Suite を試すためのプロジェクト。

## Setup

https://firebase.google.com/docs/emulator-suite/install_and_configure

上記のページの手順に従い、Emulator Suite のセットアップを行い、以下のコマンドを実行する。

```
firebase emulators:start --only firestore
```

Firebase コンソールから `google-services.json` をダウンロードして、app ディレクトリの下に配置する。

## 参考

- https://firebase.google.com/docs/emulator-suite/connect_and_prototype