---
sidebar_position: 1
---

# Overview

## Korest Docs

&nbsp; Korest Docs is a Kotlin DSL-based API documentation library that makes using Spring REST Docs easier and more convenient. While Spring REST Docs offers powerful features, its configuration can be complex and requires a lot of repetitive code. Korest Docs addresses these issues by leveraging
Kotlin’s DSL style, enabling API documentation to be written in a more intuitive and declarative manner.

&nbsp; This allows developers to reduce unnecessary boilerplate code and focus on documenting their APIs. Korest Docs provides various convenience features such as type inference, automatic default snippet configuration, and Swagger UI conversion, delivering a much more concise and productive
documentation environment while maintaining the functionality of Spring REST Docs. (Some features are still under development.)

---

## Why use Korest Docs?

### 1. Enhancing API Documentation

&nbsp; Korest Docs allows developers to write API documentation in a declarative manner using Kotlin DSL. This approach significantly improves readability and makes it easy to create concise and well-structured API specifications.

&nbsp; For example, by using requestWithDocs() and andDocument(), you can easily integrate with Spring REST Docs as follows:

```kotlin
mockMvc.requestWithDocs(HttpMethod.GET, "/example/{id}", 1) {
    header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
    param("param1", "value1")
}
    .andExpect {
        status { isOk() }
    }
    .andDocument("identifier") {
        requestHeader {
            header("Authorization", "Access Token", "Bearer access-token")
        }

        pathParameter {
            pathVariable("id", "id", 1)
        }

        requestParameter {
            queryParameter("param1", "parameter 1", "value1")
        }

        responseField {
            field("code", "Response Code", ReturnCode.SUCCESS.code)
            field("message", "Response Message", ReturnCode.SUCCESS.message)
            field("data", "Response Data", data)
            optionalField("data.message", "message", data.message)
            field("data.userId", "User Id", data.userId)
        }
    }
    .andDo {
        // TO-DO
    }
```

&nbsp; If the primary goal is API documentation without additional processing using andDo(), you can simply document the API as follows:

```kotlin
documentation("identifier") {
    request(HttpMethod.GET, "/example/{id}") {
        pathVariable("id", "id", 1)
    }

    requestHeader {
        header("Authorization", "Access Token", "Bearer access-token")
    }

    requestParameter {
        queryParameter("param1", "parameter 1", "value1")
    }

    responseField {
        field("code", "Response Code", ReturnCode.SUCCESS.code)
        field("message", "Response Message", ReturnCode.SUCCESS.message)
        field("data", "Response Data", data)
        optionalField("data.message", "message", data.message)
        field("data.userId", "User Id", data.userId)
    }
}
```

&nbsp; This approach allows developers to focus purely on API documentation, making it more readable and structured while reducing unnecessary boilerplate code.

### 2. Automatic Type Inference

&nbsp; Korest Docs automatically infers types from request and response models, reducing unnecessary repetitive work. Below is the field() inline function from FieldsSpec:

```kotlin
inline fun <reified T : Any> field(
    path: String,
    description: String?,
    example: T,
    attributes: Map<String, Any> = mapOf("optional" to false, "ignored" to false),
) {
    this.field(path, description, example, T::class, attributes)
}
```

&nbsp; By leveraging Reified Type Parameters in Kotlin’s inline functions, Korest Docs can extract type information at runtime. The field() method above calls the following function:

```kotlin
override fun <T : Any> field(
    path: String,
    description: String?,
    example: T,
    type: KClass<T>,
    attributes: Map<String, Any>,
) {
    fields.putIfAbsent(path, example)
    val descriptor = PayloadDocumentation.fieldWithPath(path)
        .type(type.toFieldType().toString())
        .description(description)
        .attributes(*attributes.putFormat(type).toAttributes())

    add(descriptor)
}
```

&nbsp; This approach ensures that developers don't need to manually specify types, making API documentation more intuitive and efficient.

### 3. Restructuring Default Snippets

&nbsp; Korest Docs restructures default snippets, allowing for API documentation with extended field details such as Type and Optional attributes. Below is the field information for each element.

| Category           | Name | Type | Required | Format | Description |
|--------------------|------|------|----------|--------|-------------|
| path parameter     | O    | O    | X        | X      | O           |
| query parameter    | O    | O    | O        | O      | O           |
| request header     | O    | O    | O        | X      | O           |
| request field      | O    | O    | O        | O      | O           |
| request part       | O    | X    | O        | X      | O           |
| request part field | O    | O    | O        | O      | O           |
| response field     | O    | O    | O        | O      | O           |

---

## Post Script

&nbsp; Korest Docs plans to provide a feature that automatically converts API documentation written with Spring REST Docs into Swagger UI, enhancing visibility and improving integration with clients. Additionally, various extension features will maximize the efficiency of API documentation. More
convenient features are also under development.
