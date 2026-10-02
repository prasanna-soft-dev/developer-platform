
---

# 3. `ARCHITECTURE.md`

This documents the **actual architecture**, not imaginary future architecture.

```markdown
# System Architecture

## 1. Current Architecture

The application currently follows a full-stack architecture:

```text
                Browser
                   |
                   | HTTP / JSON
                   v
          React + Vite Frontend
                   |
                   | Axios
                   v
          Spring Boot REST API
                   |
             Service Layer
                   |
            Repository Layer
                   |
              MySQL Database