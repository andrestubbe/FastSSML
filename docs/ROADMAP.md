# FastSSML Roadmap

## Milestone Status

### Core Dialect & Builder Engine (v0.1.0)
**Status:** Released
- [x] High-performance fluent builder API (`FastSSML.create()`).
- [x] Inline single-pass XML entity escaping (`&`, `<`, `>`, `"`, `'`).
- [x] Multi-engine dialect translation (`STANDARD`, `EDGE_TTS`, `WINDOWS_SAPI`, `DEEPGRAM`, `ELEVENLABS_PLAIN`).
- [x] Prosody parameters: `voice`, `lang`, `rate`, `pitch`, `volume`, `pause`, `emphasis`, `whisper`.
- [x] Pure Java 17+ architecture with zero external dependencies.
- [x] OpenJDK JMH microbenchmark suite and interactive visual CLI demo.

---

## Upcoming Features

### SSML 1.1 W3C Phoneme & Lexicon Support
**Status:** In Progress
- [ ] Add IPA phoneme tagging: `.phoneme(String text, String alphabet, String ph)`.
- [ ] Support pronunciation lexicon references (`<lexicon src="..."/>`).

### Audio Insertion & Non-Speech Sounds
**Status:** In Progress
- [ ] Add inline audio tag support: `.audio(String urlOrPath)`.
- [ ] Background audio layer tags for supporting TTS engines.

### Streaming Character Output
**Status:** Backlog
- [ ] Direct writing into `java.io.Writer` or `java.nio.ByteBuffer` to achieve absolute 0-byte heap allocation during HTTP streaming dispatches.
