package fastssml.demo;

import fastssml.FastSSML;
import fastssml.FastSSML.Dialect;
import fastssml.FastSSML.Emphasis;

/**
 * Interactive showcase demo for FastSSML:
 * Demonstrating zero-overhead SSML generation, XML entity escaping,
 * prosody control, and multi-engine dialect translation.
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  FastSSML — Zero-Overhead SSML Dialect Engine    ");
        System.out.println("=================================================\n");

        // 1. Fluent Builder with XML escaping & prosody
        System.out.println("[1] Generating expressive multi-element SSML...");
        FastSSML builder = FastSSML.create()
                .voice("de-DE-FlorianMultilingualNeural")
                .lang("de-DE")
                .rate(1.10f)
                .pitch(1.05f)
                .volume(0.95f)
                .text("Willkommen bei FastJava! ")
                .emphasis("High-Performance", Emphasis.STRONG)
                .text(" mit 0 Kopien & 100% deterministischer Latenz. <Script> & 'Special' \"Chars\" werden automatisch escaped.")
                .pause(250)
                .text(" Bereit für maximale Synthesegeschwindigkeit.");

        // 2. Standard W3C SSML
        System.out.println("\n[2] Output: Standard W3C SSML Dialect:");
        System.out.println("-------------------------------------------------");
        System.out.println(builder.toSSML(Dialect.STANDARD));

        // 3. Edge TTS / Microsoft Speech Dialect
        System.out.println("\n[3] Output: Edge TTS Dialect:");
        System.out.println("-------------------------------------------------");
        System.out.println(builder.toSSML(Dialect.EDGE_TTS));

        // 4. Windows SAPI Dialect
        System.out.println("\n[4] Output: Windows SAPI Dialect:");
        System.out.println("-------------------------------------------------");
        System.out.println(builder.toSSML(Dialect.WINDOWS_SAPI));

        // 5. Plain Text Fallback (Strips tags for non-SSML engines like ElevenLabs plain)
        System.out.println("\n[5] Output: Plain Text (Tags Stripped):");
        System.out.println("-------------------------------------------------");
        System.out.println(builder.toPlainText());

        // 6. Micro-Throughput validation
        System.out.println("\n[6] Micro-Throughput Test (100,000 SSML builds)...");
        long start = System.nanoTime();
        for (int i = 0; i < 100_000; i++) {
            FastSSML.create()
                    .voice("en-US-JennyNeural")
                    .rate(1.0f)
                    .text("Testing FastSSML throughput " + i)
                    .pause(50)
                    .toSSML();
        }
        long elapsedNanos = System.nanoTime() - start;
        double millis = elapsedNanos / 1_000_000.0;
        double opsPerSec = (100_000.0 / elapsedNanos) * 1_000_000_000.0;

        System.out.printf("  Completed in: %.2f ms (%.0f builds/sec)\n", millis, opsPerSec);
        System.out.println("\n✔ FastSSML showcase finished successfully!");
    }
}
