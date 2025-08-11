# 📐 Tan Function – Scientific Calculator

[![Java](https://img.shields.io/badge/Java-21-blue)](https://www.oracle.com/java/)
[![Checkstyle](https://img.shields.io/badge/Style-Checkstyle-green)](https://checkstyle.sourceforge.io/)
[![SonarLint](https://img.shields.io/badge/Analysis-SonarLint-orange)](https://www.sonarlint.org/)
[![JUnit](https://img.shields.io/badge/Tests-JUnit5-brightgreen)](https://junit.org/junit5/)

**Course:** SOEN 6011 – Software Engineering Processes (Summer 2025)  
**Function:** `tan(x)` – Tangent Function (Transcendental Function F2)  
**Author:** Pretty Kotian

---

## 📜 Table of Contents
1. [Overview](#-overview)
2. [Features](#-features)
3. [Project Structure](#-project-structure)
4. [Tools & Technologies](#-tools--technologies)
5. [Development Practices (D3)](#-development-practices-d3)
6. [Testing](#-testing)
7. [Installation & Usage](#-installation--usage)
8. [Version History](#-version-history)
9. [Snapshots](#-snapshots)
10. [Repository](#-repository)

---

## 📖 Overview
This project implements the **tangent function `tan(x)`** in Java from scratch, following scientific computing best practices.  
It is part of Deliverable 3 (D3) for SOEN 6011, focusing on:
- **Code quality**
- **Styling**
- **Accessibility**
- **Testing**
- **Version control**

The application:
- Features a GUI using **Java Swing**
- Follows a consistent programming style verified via **Checkstyle**
- Analyzed using **SonarLint**
- Debugged using **JDB**
- Versioned with **Semantic Versioning**
- Accessible for inclusive use
- Tested with **JUnit**

---

## ✨ Features
- **Mathematical accuracy** – Custom tangent calculation without Java's `Math.tan()`
- **Error handling** – Shows "undefined" for mathematically undefined angles (`90°`, `270°`, etc.)
- **GUI design** – User-friendly and accessible interface
- **Accessibility** – Java Accessibility API principles applied
- **Code quality** – Verified with Checkstyle & SonarLint
- **Testing** – Verified with JUnit for functional correctness

---

## 📂 Project Structure
```
SOEN6011-TanFunction/
├── src/
│   └── tancalculator/
│       ├── TanCalculator.java
│       ├── TanCalculatorGUI.java
│   └── Main.java
├── test/
│   └── tancalculator/
│       └── TanCalculatorGUITest.java
├── Screenshot/
│   └── 
├── checkstyle.xml
├── version.txt
├── .gitignore
└── README.md
```

---

## ⚙️ Tools & Technologies
- **Java 21**
- **Java Swing**
- **Checkstyle** (style compliance)
- **SonarLint** (static code analysis)
- **JDB** (Java Debugger)
- **JUnit 5** (unit testing)
- **Git & GitHub** (version control & hosting)

---

## 📊 Development Practices (D3)
✅ **Programming Style** – [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)  
✅ **Styling Tool** – Checkstyle (0 violations)  
✅ **Debugger** – JDB (runtime inspection)  
✅ **Static Analysis** – SonarLint (no critical issues)  
✅ **Semantic Versioning** – v1.2.0  
✅ **Accessibility** – Screen reader & keyboard navigation tested  
✅ **Unit Testing** – JUnit coverage for valid, invalid, and edge cases

---

## 🧪 Testing
Example JUnit test:
```java
@Test
public void testTan90Degrees() {
    assertThrows(ArithmeticException.class, () -> tanFunction.calculate(90));
}
```

---

## 📦 Installation & Usage
```bash
# Clone the repository
git clone https://github.com/PrettyKotian/SOEN6011-TanFunction.git
cd SOEN6011-TanFunction

# Compile
javac -d bin src/tancalculator/*.java

# Run
java -cp bin tancalculator.Main
```

---
## 📜 Version History
| Version | Date       | Changes                                                      |
| ------- | ---------- | ------------------------------------------------------------ |
| 1.0.0   | 2025-07-15 | Initial D2 implementation                                    |
| 1.1.0   | 2025-07-25 | GUI enhancements, bug fixes                                  |
| 1.2.0   | 2025-08-10 | D3 updates – style, debugger, static analysis, accessibility |

---
## 📸 Snapshots

### ✅ Checkstyle
| Description | Screenshot |
|-------------|------------|
| **16 Violations** | ![Checkstyle – 16 violations](screenshot/checkstyle-violations-16.jpeg) |
| **10 Violations** | ![Checkstyle – 10 violations](screenshot/checkstyle-violations-10.jpeg) |
| **3 Violations** | ![Checkstyle – 3 violations](screenshot/checkstyle-violations-3.jpeg) |
| **Pass – 0 violations** | ![Checkstyle – pass with no violations](screenshot/checkstyle-pass.jpeg) |

---

### 🐞 Debugger (JDB / IDE)
| Description | Screenshot |
|-------------|------------|
| **Breakpoint – Input Handling** | ![Debugger – computeTan input](screenshot/debugger-computeTan-input.jpeg) |
| **Breakpoint – Variables View** | ![Debugger – computeTan variables](screenshot/debugger-computeTan-variables.jpeg) |
| **Breakpoint – Calculation Result** | ![Debugger – computeTan results](screenshot/debugger-computeTan-results.jpeg) |

---

### 🔍 SonarLint Analysis
| Description | Screenshot |
|-------------|------------|
| **Initial Scan – Issues Found** | ![SonarLint – issues initial](screenshot/sonarlint-issues-initial.jpeg) |
| **After Fixes – No Issues** | ![SonarLint – no issues](screenshot/sonarlint-no-issues.jpeg) |

---

### 🧪 JUnit Tests
| Description | Screenshot |
|-------------|------------|
| **All Tests Passed** | ![JUnit – all tests passed](screenshot/junit-test-run-results.jpeg) |

---

### 🎨 GUI
| Description | Screenshot |
|-------------|------------|
| **Main Window** | ![GUI – main window](screenshot/gui-main-window.jpeg) |
| **Calculation – Degrees** | ![GUI – calculation in degrees](screenshot/gui-example-calculation-degree.jpeg) |
| **Calculation – Radians** | ![GUI – calculation in radians](screenshot/gui-example-calculation-radians.jpeg) |
| **Error Message** | ![GUI – calculation error](screenshot/gui-example-calculation-error.jpeg) |
| **Invalid Input Case** | ![GUI – invalid input](screenshot/gui-example-invalid-input.jpeg) |
| **Cleared Input** | ![GUI – cleared input](screenshot/gui-example-input-cleared.jpeg) |

---

## 🌐 Repository

[SOEN6011-TanFunction GitHub Repo](https://github.com/PrettyKotian/SOEN6011-TanFunction)
