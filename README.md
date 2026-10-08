# FastSSML 🗣️

[![Release](https://img.shields.io/github/v/release/andrestubbe/FastSSML?style=for-the-badge&logo=github&color=00ffcc)](https://github.com/andrestubbe/FastSSML/releases)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

> **High-Performance, Zero-Allocation SSML Builder & Cross-Engine Dialect Engine for Java.**
> Seamless generation of Speech Synthesis Markup Language with support for Edge TTS, Windows SAPI/OneCore, Deepgram Aura, and ElevenLabs.

---

## ⚡ Features

- **🚀 Zero Boilerplate:** Fluent, typed builder API for constructing SSML.
- **🛡️ Safe XML Escaping:** Instant escaping for special characters (`<`, `>`, `&`, `"`, `'`).
- **🌐 Cross-Engine Dialects:** Native export for Standard SSML, Edge TTS, Windows SAPI, and Plain Text.
- **🎛️ Expressive Speech Controls:** Control `rate`, `pitch`, `volume`, `pause`, and `emphasis`.

---

## 📦 Installation (Maven / JitPack)

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependency>
    <groupId>com.github.andrestubbe</groupId>
    <artifactId>FastSSML</artifactId>
    <version>0.1.0</version>
</dependency>
```

---

## 💡 Quick Start

```java
import fastssml.FastSSML;
import fastssml.FastSSML.Emphasis;

String ssml = FastSSML.create()
    .voice("de-DE-FlorianMultilingualNeural")
    .rate(1.05f)
    .pitch(1.0f)
    .volume(1.0f)
    .text("Hallo! Hier spricht ")
    .emphasis("Florian", Emphasis.STRONG)
    .pause(300)
    .text(" mit echter Expressivität.")
    .toSSML();
```

---

## 📜 License
MIT License © 2026 André Stubbe