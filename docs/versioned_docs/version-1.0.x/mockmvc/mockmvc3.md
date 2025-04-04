---
sidebar_position: 2
---

# Declarative Function

&nbsp; Korest Docs also supports declarative functions for API specification.

&nbsp; Below is an example of how to define documentation using a declarative function.

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

&nbsp; When using declarative functions to define APIs, we can focus solely on the specification itself, significantly improving code readability. These declarative functions support not only basic HTTP requests but also multipart requests.

&nbsp; The following is an example of specifying a multipart request API using declarative functions.

```kotlin
documentation("junit-multipart-post-function") {
    multipart(HttpMethod.POST, "/example")

    requestPart {
        part("images", "Images", image1)
        part("images", "Images", image2)
    }
}
```
