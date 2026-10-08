# FastXXX API Reference Manual

`FastXXX` provides ultra-fast native Windows primitives directly on the JVM with zero external runtime bloat. It bypasses legacy abstraction layers to deliver deterministic sub-microsecond latency, zero garbage collection allocations, and immediate OS hardware integration.

---

## 1. Class: `fastxxx.FastXXX`

Central facade providing high-performance operations and state accessors.

### Methods

| Method / Signature | Return Type | Description |
|:---|:---|:---|
| `doSomethingNative()` | `void` | Executes high-speed native operation via JNI or Java 21+ FFM. |
| `getState()` | `int` | Queries current native execution state flags. |
| `reset()` | `boolean` | Resets native kernel context, state flags, and internal buffers. |

---

## 2. JNI & FFM Memory Contracts

- **Zero-Allocation**: Hot-path methods operate on primitive registers, flat arrays, or pre-allocated direct memory buffers with **0 bytes GC churn**.
- **Memory Pinning / Segments**: JNI methods use critical array pinning or direct byte buffers. Modern FFM implementations leverage `java.lang.foreign.MemorySegment` and arena allocators.
- **Thread-Safety**: Static query and inspection methods are thread-safe and re-entrant.

---

## 3. CPU Feature Model

- **AVX2 / AVX-512**: Detected via CPUID or system feature queries. Enables 32-byte / 64-byte vector operations.
- **SSE4.2**: 16-byte fallback vector path.
- **Fallback Rule**: AVX2 → SSE4.2 → Scalar.

---

## 4. Platform Support

| Platform | Architecture | Status | Backend |
|:---|:---|:---|:---|
| Windows 10/11 | x64, ARM64 | ✅ Fully Supported | Win32 / DirectX Native or FFM |
| Linux | x64, ARM64 | 🚧 Planned | Native bindings / pure Java fallback |
| macOS | Apple Silicon, x64 | 🚧 Planned | Native bindings / pure Java fallback |

---

**Part of the FastJava Ecosystem** — *Making the JVM faster.*
