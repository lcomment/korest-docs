---
sidebar_position: 2
---

# HTTP Request

&nbsp; MockMvc provides HTTP request handling by default, and Korest Docs includes extension functions for MockMvc related to Spring REST Docs. If you need to validate and perform additional actions after sending a request with MockMvc using andDo(), you can leverage these extension functions.

&nbsp; Below is an example utilizing these extensions:

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
        // omitted
    }
```

&nbsp; The following are the key MockMvc extension functions provided by Korest Docs.

### Request With HTTP Method

```kotlin
fun MockMvc.requestWithDocs(
    method: HttpMethod,
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.requestWithDocs(
    method: HttpMethod,
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```

### GET

```kotlin
fun MockMvc.getWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.getWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```

### POST

```kotlin
fun MockMvc.postWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.postWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```

### PUT

```kotlin
fun MockMvc.putWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.putWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```

### DELETE

```kotlin
fun MockMvc.deleteWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.deleteWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```
