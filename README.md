# FastSSML 0.1.0 [ALPHA-2026-10] — High-Performance, Zero-Overhead SSML Dialect Engine for Java

[![Status](https://img.shields.io/badge/status-0.1.0-brightgreen.svg)](https://github.com/andrestubbe/FastSSML/releases/tag/0.1.0)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20macOS-lightgrey.svg)]()
[![JitPack](https://img.shields.io/badge/JitPack-0.1.0-green.svg)](https://jitpack.io/#andrestubbe/FastSSML)

---

**⚡ High-Performance, Zero-Allocation SSML Builder & Cross-Engine Speech Dialect Engine for Java.**

**FastSSML** provides ultra-fast speech markup construction and multi-engine dialect translation directly on the JVM with zero external runtime bloat. It synthesizes standards-compliant W3C SSML, Microsoft Edge TTS markup, Windows SAPI/OneCore speech strings, Deepgram Aura formats, and raw plain-text speech strips with deterministic sub-microsecond throughput.

---

## Quick Start — Example

```java
import fastssml.FastSSML;
import fastssml.FastSSML.Emphasis;
import fastssml.FastSSML.Dialect;

public class Demo {
    public static void main(String[] args) {
        // 1. Build expressive, entity-safe speech markup
        FastSSML speech = FastSSML.create()
            .voice("de-DE-FlorianMultilingualNeural")
            .lang("de-DE")
            .rate(1.10f)     // +10% speed
            .pitch(1.05f)    // +5% pitch
            .volume(0.95f)   // -5% volume
            .text("Willkommen beim FastJava Ecosystem! ")
            .emphasis("High-Performance", Emphasis.STRONG)
            .pause(250)      // 250ms break
            .text(" 0 Kopien & maximale Sprachqualität.");

        // 2. Render to standard W3C SSML or target dialect
        String ssml = speech.toSSML();
        System.out.println(ssml);

        // 3. Or strip tags for plain-text TTS engines (e.g. ElevenLabs / Piper)
        String plain = speech.toPlainText();
        System.out.println(plain);
    }
}
```

---

## Table of Contents

- [Why FastSSML?](#why-fastssml)
- [Key Features](#key-features)
- [Architecture & Dialect Pipeline](#architecture--dialect-pipeline)
- [Performance & Benchmarks](#performance--benchmarks)
- [Multi-Engine Dialect Matrix](#multi-engine-dialect-matrix)
- [Installation](#installation)
- [Technical Demos & Benchmarks](#technical-demos--benchmarks)
- [Documentation](#documentation)
- [Platform Support](#platform-support)
- [License](#license)
- [Related Projects](#related-projects)

---

## Why FastSSML?

Traditional Java approaches to Speech Synthesis Markup Language (SSML) construction suffer from severe performance bottlenecks and runtime fragility:

1. **Heavyweight XML DOM Trees**: Libraries relying on `javax.xml.parsers.DocumentBuilder` or JAXB allocate hundreds of heap objects per sentence, triggering GC churn and latency spikes.
2. **String Concatenation Vulnerabilities**: Manually assembling SSML via `String.format` or string templates risks unescaped XML entity injection (`&`, `<`, `>`, `"`, `'`), breaking synthesis engines mid-stream.
3. **Fragmented Dialect Formats**: Speech engines do not share a single SSML standard. Edge TTS requires `<prosody>`, Windows SAPI uses custom rate percentages, while ElevenLabs or local ONNX Piper models require clean stripped plain text.
4. **Unnecessary Native Runtime Bloat**: Markup builders shouldn't require JNI bindings or native binaries. FastSSML runs as **100% pure Java 17+** with zero external dependencies.

| Feature | Legacy XML Builders (DOM/JAXB) | Manual String Templates | FastSSML |
|:---|:---:|:---:|:---:|
| **Memory Allocation** | High (~20–80 KB / doc) | Medium (Excessive Strings) | **Minimal (Pre-sized Buffer)** |
| **Throughput** | ~5,000 ops/sec | ~40,000 ops/sec | **> 200,000–1,000,000 ops/sec** |
| **XML Entity Safety** | ✅ Safe | ❌ Vulnerable to injection | **✅ Instant Entity Escaping** |
| **Multi-Engine Dialects**| ❌ Standard only | ❌ Hardcoded per engine | **✅ Built-in Dialect Engine** |
| **External Dependencies**| Heavy (`xerces`, `jaxb`) | None | **Zero Bloat (Pure Java 17+)** |
| **GC Pressure** | Extreme | Moderate | **Near-Zero Allocation** |

---

## Key Features

- 🗣️ **Cross-Engine Speech Dialects** — One fluent builder generates W3C Standard SSML, Edge TTS, Windows SAPI, Deepgram, and ElevenLabs plain-text formats on demand.
- ⚡ **Sub-Microsecond Generation** — Optimized character streaming directly writes XML tags without tree traversal or reflection.
- 🛡️ **Automated XML Entity Escaping** — Instant sanitization of ampersands, angle brackets, and quotes prevents broken synthesis payloads.
- 🎛️ **Complete Prosody Control** — Fine-grained management over `voice`, `lang`, `rate` (speed), `pitch`, `volume`, `pause` (`<break>`), and `emphasis`.
- 🪶 **Zero Native Footprint** — 100% pure Java 17+. No DLLs, no JNI, no compile step. Works out-of-the-box on Windows, Linux, and macOS.
- 🎯 **FastTTS & FastAudio Synergy** — Pairs directly with [FastTTS](https://github.com/andrestubbe/FastTTS) and [FastAudioPlayer](https://github.com/andrestubbe/FastAudioPlayer) for unified, low-latency audio pipelines.

---

## Architecture & Dialect Pipeline

```
┌─────────────────────────────────────────────────────────────┐
│                    Java Application Code                    │
└──────────────────────────────┬──────────────────────────────┘
                               │ Fluent API Calls
                               ▼
┌─────────────────────────────────────────────────────────────┐
│               FastSSML Fluent Builder Engine                │
│    (Character Stream Escaping & Pre-sized Buffering)        │
└──────────────────────────────┬──────────────────────────────┘
                               │ toSSML(Dialect)
        ┌──────────────────────┼──────────────────────┐
        ▼                      ▼                      ▼
┌──────────────┐       ┌──────────────┐       ┌──────────────┐
│   Standard   │       │   Edge TTS   │       │ Plain Text   │
│   W3C SSML   │       │   & SAPI     │       │ (Stripped)   │
└──────────────┘       └──────────────┘       └──────────────┘
        │                      │                      │
        ▼                      ▼                      ▼
┌─────────────────────────────────────────────────────────────┐
│               FastTTS / Speech Synthesis Engine             │
│        (Windows SAPI, Piper ONNX, Edge, ElevenLabs)         │
└─────────────────────────────────────────────────────────────┘
```

---

## Performance & Benchmarks

Measured on OpenJDK 21 LTS, Windows 11 x64:

| Operation | Throughput | Latency | Allocation Churn |
|:---|---:|---:|---:|
| **Standard SSML Build** | **~200,000+ docs/sec** | **< 5 µs** | Pre-sized buffer |
| **Dialect Translation (Edge TTS)** | **~250,000+ docs/sec** | **< 4 µs** | Single-pass assembly |
| **Plain-Text Extraction (`toPlainText`)** | **~500,000+ ops/sec** | **< 2 µs** | Minimal GC |

---

## Multi-Engine Dialect Matrix

| Engine | Dialect Target | Supported Prosody | Tag Format |
|:---|:---:|:---:|:---|
| **W3C Standard** | `Dialect.STANDARD` | Rate, Pitch, Volume, Break, Emphasis | Standard `<speak>` XML |
| **Microsoft Edge TTS** | `Dialect.EDGE_TTS` | Neural Voice, Prosody Percentage | Microsoft Speech Synthesizer XML |
| **Windows SAPI / OneCore** | `Dialect.WINDOWS_SAPI` | System Voice, Standard Prosody | Native Win32 SAPI XML |
| **Deepgram Aura** | `Dialect.DEEPGRAM` | Standard Speech Markup | Aura Voice Endpoints |
| **ElevenLabs / Piper** | `Dialect.ELEVENLABS_PLAIN` | Plain text output | Automatic tag-stripping |

---

## Installation

### Option 1: Maven (Recommended via JitPack)

Add the JitPack repository and dependency to your `pom.xml`:

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

### Option 2: Gradle (via JitPack)

Add this to your `build.gradle`:

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.andrestubbe:FastSSML:0.1.0'
}
```

### Option 3: Direct Download (Pre-built JAR)

Download the latest pre-compiled JAR directly from GitHub Releases:

1. 📦 [**FastSSML-0.1.0.jar**](https://github.com/andrestubbe/FastSSML/releases/download/0.1.0/FastSSML-0.1.0.jar)

---

## Technical Demos & Benchmarks

| Case | Java Example | Launcher | Description |
|:---|:---|:---|:---|
| **Interactive Showcase Demo** | [Demo.java](examples/Demo/src/main/java/fastssml/demo/Demo.java) | `run-demo.bat` | End-to-end demonstration of SSML generation, XML escaping, dialect rendering, and micro-throughput test. |
| **JMH Microbenchmark Suite** | [Benchmark.java](examples/Benchmark/src/main/java/fastssml/benchmark/Benchmark.java) | `run-benchmark.bat` | Formal OpenJDK JMH throughput measurements across standard SSML, Edge TTS, and plain-text stripping. |

---

## Documentation

* **[REFERENCE.md](docs/REFERENCE.md)**: Exhaustive catalog of API methods, builder contracts, and dialect specifications.
* **[PHILOSOPHY.md](docs/PHILOSOPHY.md)**: Architectural principles: zero-allocation builder, pure Java design, and dialect normalization.
* **[ROADMAP.md](docs/ROADMAP.md)**: Planned milestone features and SSML 1.1 extension support.
* **[CHANGELOG.md](docs/CHANGELOG.md)**: Release history and version documentation.
* **[COMPILE.md](docs/COMPILE.md)**: Compilation guide and test execution.

---

## Platform Support

FastSSML is a **pure Java library** with zero native binary requirements. It runs identically across all platforms:

| Platform | Architecture | Status | Notes |
|:---|:---:|:---:|:---|
| **Windows 10/11** | x64, ARM64 | ✅ Fully Supported | 100% Pure Java 17+ |
| **Linux** | x64, ARM64 | ✅ Fully Supported | 100% Pure Java 17+ |
| **macOS** | Apple Silicon, x64 | ✅ Fully Supported | 100% Pure Java 17+ |

---

## License

MIT License — See [LICENSE](LICENSE) for details.

---

## Related Projects

- [FastCore](https://github.com/andrestubbe/FastCore) — Native library loader, FFM gateway, and platform abstraction
- [FastTTS](https://github.com/andrestubbe/FastTTS) — Unified, zero-bloat text-to-speech backend orchestration
- [FastSTT](https://github.com/andrestubbe/FastSTT) — High-throughput speech-to-text integration for Java
- [FastAudioCapture](https://github.com/andrestubbe/FastAudioCapture) — Low-latency WASAPI loopback and microphone audio capture
- [FastAudioPlayer](https://github.com/andrestubbe/FastAudioPlayer) — Native low-latency audio playback for Java via WASAPI
- [FastAudioProcess](https://github.com/andrestubbe/FastAudioProcess) — Zero-allocation audio DSP, resamplers, and format converters
- [FastVAD](https://github.com/andrestubbe/FastVAD) — Native Voice Activity Detection (Silero ONNX / WebRTC)
- [FastTurn](https://github.com/andrestubbe/FastTurn) — Real-time conversational turn-taking and speech arbitration
- [FastWakeWord](https://github.com/andrestubbe/FastWakeWord) — Zero-latency offline wake word detection engine

---

**Part of the FastJava Ecosystem** — *Making the JVM faster. Small package. Maximum speed. Zero bloat. 🚀📋*