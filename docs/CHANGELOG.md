# Changelog — FastSSML 📜

All notable changes to **FastSSML** will be documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/), and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [0.1.0] — 2026-10-08

### Added
- **Core Architecture**:
  - `FastSSML` fluent builder providing entity-safe SSML construction.
  - Cross-engine dialect enumeration (`FastSSML.Dialect`): `STANDARD`, `EDGE_TTS`, `WINDOWS_SAPI`, `DEEPGRAM`, and `ELEVENLABS_PLAIN`.
  - Acoustic emphasis levels (`FastSSML.Emphasis`): `NONE`, `REDUCED`, `MODERATE`, and `STRONG`.
  - Single-pass XML entity escaping for special characters (`&`, `<`, `>`, `"`, `'`).
  - Prosody adjustment methods: `rate`, `pitch`, `volume`, `pause`, and `whisper`.
  - `toPlainText()` utility to strip all SSML tags for plain-text TTS backends (e.g. ElevenLabs, Piper ONNX).
- **Tooling & Examples**:
  - `examples/Demo`: Interactive CLI showcase exercising SSML generation across all dialects.
  - `examples/Benchmark`: JMH microbenchmark suite measuring throughput and memory allocation.
  - `run-demo.bat` and `run-benchmark.bat` launcher scripts.
  - Complete documentation suite (`README.md`, `REFERENCE.md`, `PHILOSOPHY.md`, `ROADMAP.md`, `COMPILE.md`).
