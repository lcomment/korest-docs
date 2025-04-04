---
sidebar_position: 2
---

# Applying Korest Docs

> ### ✅ Korest Docs works in a Kotlin + Spring Framework environment utilizing Spring REST Docs.

### 1. Add ExtendWith

&nbsp; To use Korest Docs, you need to register KorestDocumentationExtension with @ExtendWith, just as you would configure RestDocumentationExtension in Spring REST Docs.

```kotlin
@ExtendWith(KorestDocumentationExtension::class)
internal class ApiSpec
```

### 2. Specify API Document

Document the API using the Kotlin DSL implemented in Korest Docs.

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

## Example

&nbsp; Consider the following API:

```kotlin
@GetMapping("/example/{id}")
fun get(
    @PathVariable id: Long,
): ApiResponse<ExampleResponse> {
    val data = exampleService.get()
    return ApiResponse.success(data)
}
```

&nbsp; To document this API using Korest Docs, you can write the following test code:

```kotlin
@SpringBootTest
@ExtendWith(KorestDocumentationExtension::class)
internal class ApiSpec {

    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var exampleService: ExampleService

    @Test
    fun `korest docs test`() {
        val data = ExampleResponse()
        doReturn(data).`when`(exampleService).get()

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
    }
}
```
