package fastxxx.demo;

import fastXXX.FastXXX;

/**
 * High-performance demonstration showcasing FastXXX capabilities.
 */
public class Demo {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("  FastXXX — Interactive Showcase Demo            ");
        System.out.println("=================================================");

        FastXXX api = new FastXXX();

        System.out.println("\n[1/2] Invoking FastXXX operation...");
        api.doSomethingNative();

        System.out.println("\n[2/2] Verification complete.");
        System.out.println("✔ FastXXX execution finished successfully!");
    }
}