package fastssml.benchmark;

import fastssml.FastSSML;
import fastssml.FastSSML.Dialect;
import fastssml.FastSSML.Emphasis;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Standard OpenJDK JMH Microbenchmark Suite for FastSSML.
 *
 * Measures raw throughput and allocation efficiency of fluent SSML construction,
 * automatic entity escaping, and dialect rendering.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 3, time = 1)
@Fork(1)
public class Benchmark {

    private String sampleText;

    @Setup
    public void setup() {
        sampleText = "High-performance speech synthesis & SSML formatting with <special> entities!";
    }

    @org.openjdk.jmh.annotations.Benchmark
    public String benchmarkStandardSSML() {
        return FastSSML.create()
                .voice("en-US-JennyNeural")
                .lang("en-US")
                .rate(1.1f)
                .pitch(1.0f)
                .volume(0.9f)
                .text(sampleText)
                .pause(100)
                .emphasis("Critical", Emphasis.STRONG)
                .toSSML(Dialect.STANDARD);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public String benchmarkEdgeTTSDialect() {
        return FastSSML.create()
                .voice("de-DE-FlorianMultilingualNeural")
                .lang("de-DE")
                .rate(1.05f)
                .text(sampleText)
                .toSSML(Dialect.EDGE_TTS);
    }

    @org.openjdk.jmh.annotations.Benchmark
    public String benchmarkPlainTextStrip() {
        return FastSSML.create()
                .text(sampleText)
                .pause(200)
                .emphasis("Stripped", Emphasis.REDUCED)
                .toPlainText();
    }
}
