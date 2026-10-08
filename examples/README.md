# FastSSML Examples

This folder contains standalone example projects demonstrating and benchmarking FastSSML.

## Demo

The `Demo` project showcases fluent SSML generation, XML entity escaping, and multi-engine dialect translation.

To run it locally:
```bash
cd Demo
mvn compile exec:java
```
Or execute `run-demo.bat` from the root directory.

## Benchmark

The `Benchmark` project measures the microbenchmark throughput of FastSSML across multiple dialects using OpenJDK JMH.

To run it:
```bash
cd Benchmark
mvn clean package
java -jar target/benchmarks.jar
```
Or execute `run-benchmark.bat` from the root directory.
