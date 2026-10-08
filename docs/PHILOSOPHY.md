# The Philosophy of FastXXX

> [!IMPORTANT]
> **"No copies. Ever. Critical FFM/JNI paths. Native-First performance."**

FastXXX is built on the principle that modern Java applications require **native-first** acceleration for performance-critical operations that the standard JVM APIs cannot fully optimize.

---

## Core Tenets

### 1. Native-First Execution
Bypass standard Java abstraction layers to reach the physical limits of the hardware using hand-tuned Win32/C++ and SIMD intrinsics or Java 21+ Foreign Function & Memory (FFM) downcalls.

### 2. Zero-Copy Architecture
Eliminate JNI transition costs and buffer copies by using direct memory access patterns, `MemorySegment`, or primitive registers between the JVM and the native layer.

### 3. Deterministic Latency & Zero GC
Eliminate variance caused by JIT warm-up or garbage collection stalls in critical hot-paths. Critical execution loops allocate **0 bytes / op** on the Java heap.

### 4. Hardware-Aware Optimization
Leverage modern CPU features (AVX2, SSE4.2, NEON) to process data at hardware-native speeds with automatic scalar fallbacks.

### 5. Blueprint Consistency
As part of the **FastJava** ecosystem, FastXXX adheres to a standardized architecture:
* **Native Backend**: Direct Win32/C++ implementation or Java 21+ FFM downcalls.
* **Unified Loading**: Powered by `FastCore` (for JNI DLL modules).
* **Telemetry & Console**: Integrated HUD diagnostic telemetry powered by `FastANSI`.
* **Standardized Layout**: Uniform `README.md`, `REFERENCE.md`, `run-demo.bat`, and OpenJDK JMH microbenchmarks (`run-benchmark.bat`).

---

**⚡ FastXXX — Powering the next generation of Native Java.**
