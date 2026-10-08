# ⚡ FastJava Repository Setup Guide

This guide details the standard procedure for initializing and pushing new FastJava repositories, including programmatic setup of metadata, topics, GitHub releases, and automated CI pipelines.

---

## 🛠️ Step-by-Step Repository Lifecycle

### 1. Initialize Local Git
Inside your newly created project directory:
```bash
git init
git branch -M main
git add -A
git commit -m "feat: initial commit for FastXXX v0.1.0"
```

### 2. Connect Remote Repository
Once the repository is created on GitHub under `andrestubbe/FastXXX`:
```bash
git remote add origin https://github.com/andrestubbe/FastXXX.git
git push -u origin main
```

---

## 🏷️ Programmatic About-Section & Tags (GitHub CLI)

To maintain a consistent, search-optimized presentation across the FastJava suite, always apply the repository description and tags programmatically using the official **GitHub CLI (`gh`)**:

### Set Description & Topics
```bash
gh repo edit andrestubbe/FastXXX \
  --description "High-Performance, Zero-Allocation native Windows XXX API for Java" \
  --add-topic "fastjava,java,zero-allocation,high-performance,windows,ffm,win32"
```

---

## 📦 GitHub Release Creation with Pre-built JAR

Publish the official GitHub Release with tag and attach the compiled artifact:

```bash
# 1. Package the JAR
mvn clean package -DskipTests

# 2. Create GitHub Release and upload JAR
gh release create 0.1.0 target/FastXXX-0.1.0.jar \
  --title "FastXXX 0.1.0 — Native Windows XXX API for Java" \
  --notes-file release/RELEASE_TEMPLATE.md
```

> [!IMPORTANT]
> Always verify that the tag name (e.g. `0.1.0`) matches the version defined in `pom.xml`, the download URL in `README.md`, and the badge metadata.

---

## 🤖 Continuous Integration Setup (GitHub Actions)

Every FastJava repository includes automatic testing on push to guarantee API stability.

Workflow file in `.github/workflows/maven.yml`:
```yaml
name: Java CI with Maven

on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'
        cache: maven
    - name: Build with Maven
      run: mvn -B package --file pom.xml
```

Once pushed, your Build Status badge in `README.md` will dynamically reflect the compilation status:
```markdown
[![Build](https://img.shields.io/github/actions/workflow/status/andrestubbe/FastXXX/maven.yml?branch=main)](https://github.com/andrestubbe/FastXXX/actions)
```
