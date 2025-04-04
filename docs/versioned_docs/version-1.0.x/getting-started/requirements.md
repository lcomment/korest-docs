---
sidebar_position: 2
---

# Requirements

> ### ✅ Korest Docs is designed for test environments using Spring REST Docs.

## Prerequisites

- Kotlin 1.9 or higher
- Spring Boot 3.0.0 or higher
- Spring REST Docs 3.0.0 or higher

## Dependencies

| Dependency	              | Description                            |
|--------------------------|----------------------------------------|
| **korest-docs-core**	    | Korest Docs Core library               |
| **korest-docs-mockmvc**  | 	Starter dependency for fixture monkey |
| **korest-docs-starter**	 | Starter dependency for Korest Docs     |

&nbsp; **korest-docs-starter** is a starter package that comes bundled with core dependencies and plugins such as support for MockMvc by default.

### Gradle

```kotlin
testImplementation("io.github.lcomment:korest-docs-starter:1.0.0")
```

### Maven

```xml

<dependency>
    <groupId>io.github.lcomment</groupId>
    <artifactId>korest-docs-starter</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

