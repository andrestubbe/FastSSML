# FastSSML API Reference Manual

`FastSSML` provides high-performance, entity-safe Speech Synthesis Markup Language (SSML) construction and cross-engine dialect rendering directly on the JVM with zero native dependencies.

---

## 1. Class: `fastssml.FastSSML`

The central builder and translator class. Instances are obtained via static factory method `FastSSML.create()`.

### Method Index

| Method Signature | Return Type | Description |
|:---|:---|:---|
| `static FastSSML create()` | `FastSSML` | Creates a new fluent builder with default pre-allocated 256-character buffer. |
| `voice(String voice)` | `FastSSML` | Sets the voice name attribute (e.g. `de-DE-FlorianMultilingualNeural`, `en-US-JennyNeural`). |
| `lang(String lang)` | `FastSSML` | Sets the document language code (defaults to `en-US`). |
| `rate(float rate)` | `FastSSML` | Sets speaking rate multiplier (e.g. `1.10f` = +10%, `0.85f` = -15%). |
| `pitch(float pitch)` | `FastSSML` | Sets speaking pitch multiplier (e.g. `1.05f` = +5%, `0.90f` = -10%). |
| `volume(float volume)` | `FastSSML` | Sets output volume multiplier (e.g. `1.00f` = standard, `0.80f` = -20%). |
| `text(String text)` | `FastSSML` | Appends speech text, automatically escaping all XML reserved entities. |
| `pause(int millis)` | `FastSSML` | Inserts a `<break time='...ms'/>` pause element. |
| `emphasis(String text, Emphasis level)` | `FastSSML` | Appends text wrapped in an `<emphasis>` tag with the given level. |
| `whisper(String text)` | `FastSSML` | Appends text with whispered speech effect (`<amazon:effect name='whispered'>`). |
| `toSSML(Dialect dialect)` | `String` | Renders the complete speech markup targeting the specified engine dialect. |
| `toSSML()` | `String` | Shortcut for `toSSML(Dialect.STANDARD)`. |
| `toPlainText()` | `String` | Strips all XML/SSML tags, returning clean plain text for non-markup TTS engines. |

---

## 2. Enums

### `FastSSML.Dialect`

Defines speech engine dialect targets:

| Constant | Description | Target Engines |
|:---|:---|:---|
| `STANDARD` | W3C Standard SSML 1.0 (`xmlns="http://www.w3.org/2001/10/synthesis"`) | Standard W3C speech synthesizers |
| `EDGE_TTS` | Microsoft Edge TTS neural speech XML format | Edge TTS, Azure Speech Service |
| `WINDOWS_SAPI` | Microsoft Win32 SAPI / OneCore XML format | Windows native SAPI voices |
| `DEEPGRAM` | Deepgram Aura conversational TTS format | Deepgram API |
| `ELEVENLABS_PLAIN` | Strips tags, outputting raw unformatted text | ElevenLabs, Piper ONNX (plain mode) |

### `FastSSML.Emphasis`

Specifies acoustic emphasis levels:

| Constant | XML Attribute Value | Acoustic Result |
|:---|:---|:---|
| `NONE` | `"none"` | No accentuation |
| `REDUCED` | `"reduced"` | Subdued accentuation |
| `MODERATE` | `"moderate"` | Standard emphasis |
| `STRONG` | `"strong"` | High-accentuation focus |

---

## 3. Entity Escaping Specification

All text passed to `text(...)` or `emphasis(...)` is sanitized in a single linear character pass:

| Character | Escaped Entity | Description |
|:---:|:---:|:---|
| `&` | `&amp;` | Ampersand |
| `<` | `&lt;` | Less-than |
| `>` | `&gt;` | Greater-than |
| `"` | `&quot;` | Double quote |
| `'` | `&apos;` | Single quote / apostrophe |

---

## 4. Concurrency & Allocation Guarantees

- **Instance Concurrency**: `FastSSML` instances are designed for single-thread fluent construction. A single builder can be re-rendered into multiple dialects (`toSSML(...)`) deterministically.
- **Allocation Profile**: Internal text storage uses a contiguous `StringBuilder` initialized to 256 characters. Rendering avoids intermediate XML DOM nodes or parse trees.
- **Pure Java Runtime**: No native DLLs, no JNI overhead, and zero native thread synchronization bottlenecks.

---

**Part of the FastJava Ecosystem** — *Making the JVM faster.*
