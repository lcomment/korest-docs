---
sidebar_position: 2
---

# Multipart Request

&nbsp; MockMvc natively supports multipart requests, and Korest Docs also provides extension functions for handling multipart requests. Below is an example using these extension functions.

```kotlin
mockMvc.multipartWithDocs(HttpMethod.POST, "/example") {
    header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
    file(image1)
    file(image2)
    param("message", "exampleMessage")
    contentType = MediaType.MULTIPART_FORM_DATA
    accept = MediaType.APPLICATION_JSON
    with {
        it.method = HttpMethod.POST.name()
        it
    }
}
    .andExpect { status { isOk() } }
    .andDocument("junit-multipart-post-extension-function") {
        requestHeader {
            header("Authorization", "Access Token", "Bearer access-token")
        }

        requestPart {
            part("images", "Images", image1)
            part("images", "Images", image2)
        }
    }
```

&nbsp; The following are the key MockMvc extension functions provided by Korest Docs.

```kotlin
fun MockMvc.multipartWithDocs(
    uri: URI,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.multipartWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.multipartWithDocs(
    httpMethod: HttpMethod,
    uri: URI,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}

fun MockMvc.multipartWithDocs(
    httpMethod: HttpMethod,
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    /* compiled code */
}
```
