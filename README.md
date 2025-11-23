# Student Management System Plus (Java Console App)

A console-based Student Management & Account System implemented in Java as part of my IT career transition learning projects.

Java を用いて実装したコンソール型の学生管理・アカウント管理システムです。  
IT エンジニアへのキャリアチェンジに向けた学習用プロジェクトの一つとして開発しました。

---

## 1. Project Summary / プロジェクト概要

**EN**

This project implements a simple **account system + student management entry point** in pure Java.  
It focuses on:

- Practicing core Java syntax and OOP
- Designing a small but realistic login / registration / password reset flow
- Implementing input validation for ID, phone number, and CAPTCHA
- Structuring code into reusable methods

The goal is to simulate a very small part of a real-world system (authentication and basic checks) while building my fundamentals for future backend and business application development.

**JP**

本プロジェクトでは、純粋な Java で **アカウント管理＋学生管理システムの入口部分** を実装しています。  
主な狙いは以下の通りです：

- Java の基本構文およびオブジェクト指向の実践
- 現実の業務システムを意識したログイン／登録／パスワード再設定フローの設計
- 身分証番号・電話番号・CAPTCHA などの入力チェック処理の実装
- メソッド分割による再利用可能なバリデーションロジックの作成

実務システムの一部（認証・入力チェック）のミニ版をイメージしつつ、将来的なバックエンド開発や業務システム開発に必要な基礎力を身につけることを目的としています。

---

## 2. Main Features / 主な機能

### 2.1 Account System / アカウント管理

**EN**

- **User Registration**
  - Username: 3–15 characters, alphanumeric only, must be unique
  - Password: double-entry confirmation
  - ID Card:
    - 18 characters
    - Cannot start with `0`
    - First 17 must be digits
    - Last character: digit or `X/x`
  - Phone Number:
    - 11 characters
    - Cannot start with `0`
    - All digits

- **Login**
  - Username existence check
  - Password check
  - CAPTCHA validation
    - 4 letters (A–Z, a–z) + 1 digit
    - Characters are randomly shuffled
    - User must input the shown CAPTCHA correctly

- **Forgot Password**
  - User inputs:
    - Username
    - ID card number
    - Phone number
  - Only if all three match the registered data, password reset is allowed

**JP**

- **ユーザー登録**
  - ユーザー名：
    - 3〜15 文字
    - 英数字のみ
    - 既存ユーザー名との重複禁止
  - パスワード：
    - 2 回入力し、一致した場合のみ登録
  - 身分証番号：
    - 全長 18 桁
    - 先頭は `0` 以外
    - 先頭 17 桁は数字
    - 最後の 1 桁は数字または `X/x`
  - 電話番号：
    - 全長 11 桁
    - 先頭は `0` 以外
    - 全て数字

- **ログイン**
  - ユーザー名の存在チェック
  - パスワードチェック
  - CAPTCHA チェック
    - 英字 4 文字（A–Z, a–z）＋数字 1 桁
    - ランダムに並び替えた 5 文字を表示
    - 表示された文字列を正しく入力した場合のみ認証成功

- **パスワード再設定**
  - 以下 3 つの情報を入力：
    - ユーザー名
    - 身分証番号
    - 電話番号
  - 3 つすべてが登録情報と一致した場合のみ、パスワードの再設定が可能

---

### 2.2 Student Management Entry / 学生管理システムへの入口

After successful login, the program creates an instance of `StudentSystem` and calls:

```java
StudentSystem ss = new StudentSystem();
ss.StartStudentSystem();
