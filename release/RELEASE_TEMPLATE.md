# FastXXX v0.1.0 — Initial Release 🚀

## 🎉 Version 0.1.0: High-Performance Native Windows XXX API for Java
**Release Date:** 2026-10-04  
**Tag:** `0.1.0`

---

## ✨ Features

- **🚀 Direct Native Performance**: Hand-tuned Win32 kernel calls linked via optimized JNI bindings or modern Java 21+ FFM downcalls.
- **⚡ Zero Garbage Collection**: Critical paths operate on primitive registers, flat arrays, or off-heap buffers with 0 bytes GC pressure.
- **⏱️ Sub-Microsecond Latency**: Built for real-time systems and autonomous agent execution loops.
- **📊 Interactive Showcase Demo**: Complete runnable demo in `examples/Demo/` via `run-demo.bat`.
- **📈 OpenJDK JMH Microbenchmarks**: Verified throughput and latency suite in `examples/Benchmark/` via `run-benchmark.bat`.

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
        <artifactId>FastXXX</artifactId>
        <version>0.1.0</version>
    </dependency>
    <!-- Required ONLY for JNI-Native Modules -->
    <dependency>
        <groupId>com.github.andrestubbe</groupId>
        <artifactId>FastCore</artifactId>
        <version>0.1.1</version>
    </dependency>
</dependencies>
```

### Direct Download (Pre-built JAR)
- 📦 [**FastXXX-0.1.0.jar**](https://github.com/andrestubbe/FastXXX/releases/download/0.1.0/FastXXX-0.1.0.jar)

---

## 🔧 Technical Details
- **Backend:** Native Win32 JNI / Java 21+ FFM downcalls.
- **Platform:** Windows 10/11 x64, ARM64.
- **Build System:** Standardized Maven pipeline with JDK 21+.

---

## 🙏 Credits
- **FastCore:** Unified native library extraction and FFM gateway.

**Part of the FastJava Ecosystem** — *Making the JVM faster.*
