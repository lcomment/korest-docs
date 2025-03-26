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

import io.github.lcomment.korestdocs.mockmvc.context.MockMvcContextHolder
import io.github.lcomment.korestdocs.mockmvc.extensions.requestWithDocs
import io.github.lcomment.korestdocs.mockmvc.extensions.urlMapping
import kotlin.collections.component1
import kotlin.collections.component2
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.ResultHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

fun documentation(
    identifier: String,
    configure: MockMvcDocumentGenerator.() -> Unit,
): ResultActionsDsl {
    val documentSpec = documentationScope(identifier).apply(configure)
    val method = documentSpec.method ?: throw IllegalArgumentException("Not Exist method")
    val urlTemplate = documentSpec.urlTemplate ?: throw IllegalArgumentException("Not Exist url template")

    val mockMvc = MockMvcContextHolder.getContext().getMockMvc()
    val headersBuilder = documentSpec.headersBuilder
    val parametersBuilder = documentSpec.parametersBuilder
    val requestFieldsBuilder = documentSpec.requestFieldsBuilder
    val mappedUrl = urlTemplate.urlMapping(parametersBuilder?.pathVariables)

    val resultActionsDsl = mockMvc.requestWithDocs(method, mappedUrl) {
        parametersBuilder?.queryParameters?.forEach { (key, value) ->
            param(key, value.toString())
        }

        headersBuilder?.headers?.forEach { (key, value) ->
            header(key, value.toString())
        }

        content = toJson(requestFieldsBuilder?.fields ?: emptyMap<String, Any>())
        contentType = MediaType.APPLICATION_JSON
    }

    return resultActionsDsl.andDo { handle(documentSpec.toResultHandler()) }
}

private fun MockMvcDocumentGenerator.toResultHandler(): ResultHandler {
    return MockMvcRestDocumentation.document(
        identifier,
        requestPreprocessor,
        responsePreprocessor,
        *snippets.toTypedArray(),
    )
}

private fun toJson(value: Any): String {
    return mapper.writeValueAsString(value)
}

private val mapper: ObjectMapper = ObjectMapper()
    .registerModule(JavaTimeModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
