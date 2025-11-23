# Student Management System Plus（Java Console App）

A small console-based student/account management system written in Java, created as part of my self-study while preparing to move into an IT engineering role in Japan.

Java で勉強しながら作成した、コンソールベースの学生管理・アカウント管理システムです。  
業務システムの「ログイン周り」や「入力チェック」のイメージをつかむことを意識して作りました。

---

## 1. Introduction / はじめに

This project started as a simple practice assignment, but I tried to treat it more like a tiny real-world system rather than just a “Hello World” level exercise.

- There is a **login / register / forgot password** account flow.
- Input values are actually validated (ID card, phone number, username format, etc.).
- After login, control is passed to a separate `StudentSystem` class, which is intended to handle student data.

I am currently learning Java with the goal of transitioning into IT (e.g. 社内SE, backend, test engineer).  
So in this project, I focused not only on “making it run”, but also on how to break logic into methods, think about validation rules, and design a flow that feels a bit closer to what business applications do.

このプロジェクトは単なる練習問題ではなく、「実際のシステムならこういうチェックをしそうだな」という感覚を掴むことを意識して作りました。  
ログイン機能やバリデーション処理を通して、今後の業務システム開発に繋がる基礎力を身につけることが狙いです。

---

## 2. What this app does / できること

起動すると、まず簡単なメニューが表示されます。

```text
欢迎来到学生管理系统
请选择操作  1.登录 2.注册 3.忘记密码
