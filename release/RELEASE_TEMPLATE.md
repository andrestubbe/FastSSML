# FastSSML v0.1.0 — Initial Release 🚀

## 🎉 Version 0.1.0: High-Performance, Zero-Overhead SSML Dialect Engine for Java
**Release Date:** 2026-10-08  
**Tag:** `0.1.0`

---

## ✨ Features

- **🚀 Zero-Overhead SSML Generation**: Fluent typed builder for constructing speech markup with near-zero allocation and sub-microsecond latency.
- **🛡️ Instant XML Entity Escaping**: Automatic sanitization of ampersands, angle brackets, and quotes in a single pass.
- **🌐 Cross-Engine Speech Dialects**: Out-of-the-box translation for W3C Standard SSML, Microsoft Edge TTS, Windows SAPI/OneCore, Deepgram Aura, and ElevenLabs plain text.
- **🎛️ Full Prosody Control**: Fluent methods for rate, pitch, volume, breaks (`<break time="...ms"/>`), emphasis levels, and whisper effects.
- **🪶 100% Pure Java 17+**: Zero native C++ binaries, zero DLLs, zero external dependencies. Runs identically on Windows, Linux, and macOS.
- **📊 Interactive Showcase Demo**: Complete runnable demo in `examples/Demo/` via `run-demo.bat`.
- **📈 OpenJDK JMH Microbenchmarks**: Verified throughput benchmark suite in `examples/Benchmark/` via `run-benchmark.bat`.

---

## 📦 Installation (JitPack)

### Maven
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>FastSSML</artifactId>
        <version>0.1.0</version>
    </dependency>
</dependencies>
```

### Direct Download (Pre-built JAR)
- 📦 [**FastSSML-0.1.0.jar**](https://github.com/andrestubbe/FastSSML/releases/download/0.1.0/FastSSML-0.1.0.jar)

---

## 🔧 Technical Details
- **Architecture:** 100% Pure Java 17+.
- **Platform:** Cross-platform (Windows, Linux, macOS).
- **Build System:** Standardized Maven pipeline with JDK 17+.

---

**Part of the FastJava Ecosystem** — *Making the JVM faster.*
