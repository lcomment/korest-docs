/*
 * Korest Docs
 *
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.lcomment.korestdocs.mockmvc

import java.net.URI
import org.springframework.http.HttpMethod
import org.springframework.restdocs.generate.RestDocumentationGenerator
import org.springframework.test.web.servlet.MockHttpServletRequestDsl
import org.springframework.test.web.servlet.MockMultipartHttpServletRequestDsl
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.head
import org.springframework.test.web.servlet.multipart
import org.springframework.test.web.servlet.options
import org.springframework.test.web.servlet.patch
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.request

fun MockMvc.requestWithDocs(
    method: HttpMethod,
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return request(method, uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.requestWithDocs(
    method: HttpMethod,
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return request(method, urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.headWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return head(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.headWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return head(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.optionsWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return options(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.optionsWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return options(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.multipartWithDocs(
    uri: URI,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return multipart(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.multipartWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return multipart(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.multipartWithDocs(
    httpMethod: HttpMethod,
    uri: URI,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return multipart(httpMethod, uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.multipartWithDocs(
    httpMethod: HttpMethod,
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockMultipartHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return multipart(httpMethod, urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.getWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return get(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.getWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return get(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.postWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return post(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.postWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return post(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.putWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return put(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.putWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return put(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.patchWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return patch(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.patchWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return patch(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}

fun MockMvc.deleteWithDocs(
    uri: URI,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return delete(uri) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, uri.toString())
        dsl()
    }
}

fun MockMvc.deleteWithDocs(
    urlTemplate: String,
    vararg vars: Any?,
    dsl: MockHttpServletRequestDsl.() -> Unit = {},
): ResultActionsDsl {
    return delete(urlTemplate, *vars) {
        requestAttr(RestDocumentationGenerator.ATTRIBUTE_NAME_URL_TEMPLATE, urlTemplate)
        dsl()
    }
}
