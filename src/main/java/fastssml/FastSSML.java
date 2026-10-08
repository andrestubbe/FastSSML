package fastssml;

import java.util.Locale;

/**
 * FastSSML — High-Performance, Zero-Overhead SSML Builder and Cross-Engine Dialect Generator.
 */
public final class FastSSML {

    public enum Dialect {
        STANDARD,
        EDGE_TTS,
        WINDOWS_SAPI,
        DEEPGRAM,
        ELEVENLABS_PLAIN
    }

    public enum Emphasis {
        NONE("none"),
        REDUCED("reduced"),
        MODERATE("moderate"),
        STRONG("strong");

        private final String val;
        Emphasis(String val) { this.val = val; }
        public String value() { return val; }
    }

    private final StringBuilder sb = new StringBuilder(256);
    private String voice = null;
    private String lang = "en-US";
    private float rate = 1.0f;
    private float pitch = 1.0f;
    private float volume = 1.0f;

    private FastSSML() {}

    public static FastSSML create() {
        return new FastSSML();
    }

    public FastSSML voice(String voice) {
        this.voice = voice;
        return this;
    }

    public FastSSML lang(String lang) {
        this.lang = lang;
        return this;
    }

    public FastSSML rate(float rate) {
        this.rate = rate;
        return this;
    }

    public FastSSML pitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public FastSSML volume(float volume) {
        this.volume = volume;
        return this;
    }

    public FastSSML text(String text) {
        if (text != null) {
            escapeXml(sb, text);
        }
        return this;
    }

    public FastSSML pause(int millis) {
        sb.append("<break time='").append(millis).append("ms'/>");
        return this;
    }

    public FastSSML emphasis(String text, Emphasis level) {
        sb.append("<emphasis level='").append(level.value()).append("'>");
        escapeXml(sb, text);
        sb.append("</emphasis>");
        return this;
    }

    public FastSSML whisper(String text) {
        sb.append("<amazon:effect name='whispered'>");
        escapeXml(sb, text);
        sb.append("</amazon:effect>");
        return this;
    }

    /**
     * Converts to SSML string using the specified speech dialect.
     */
    public String toSSML(Dialect dialect) {
        if (dialect == Dialect.ELEVENLABS_PLAIN) {
            return toPlainText();
        }

        String rateStr = String.format(Locale.US, "%+d%%", Math.round((rate - 1.0f) * 100));
        String pitchStr = String.format(Locale.US, "%+d%%", Math.round((pitch - 1.0f) * 100));
        String volStr = String.format(Locale.US, "%+d%%", Math.round((volume - 1.0f) * 100));

        StringBuilder out = new StringBuilder(sb.length() + 180);
        out.append("<speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xml:lang='").append(lang).append("'>");
        
        if (voice != null && !voice.isBlank()) {
            out.append("<voice name='").append(voice).append("'>");
        }

        out.append("<prosody rate='").append(rateStr).append("' pitch='").append(pitchStr).append("' volume='").append(volStr).append("'>");
        out.append(sb);
        out.append("</prosody>");

        if (voice != null && !voice.isBlank()) {
            out.append("</voice>");
        }

        out.append("</speak>");
        return out.toString();
    }

    public String toSSML() {
        return toSSML(Dialect.STANDARD);
    }

    /**
     * Strips all tags and returns pure human speech text.
     */
    public String toPlainText() {
        return sb.toString().replaceAll("<[^>]+>", "");
    }

    private static void escapeXml(StringBuilder out, String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            switch (c) {
                case '&' -> out.append("&amp;");
                case '<' -> out.append("&lt;");
                case '>' -> out.append("&gt;");
                case '\"' -> out.append("&quot;");
                case '\'' -> out.append("&apos;");
                default -> out.append(c);
            }
        }
    }
}
