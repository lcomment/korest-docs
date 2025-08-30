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
import io.github.lcomment.korestdocs.mockmvc.extensions.documentationScope
import io.github.lcomment.korestdocs.mockmvc.extensions.extractPathParameters
import io.github.lcomment.korestdocs.mockmvc.extensions.multipartWithDocs
import io.github.lcomment.korestdocs.mockmvc.extensions.requestWithDocs
import io.github.lcomment.korestdocs.mockmvc.extensions.toMockMultipartFile
import io.github.lcomment.korestdocs.mockmvc.extensions.toResultHandler
import io.github.lcomment.korestdocs.spec.FieldsSpec
import io.github.lcomment.korestdocs.spec.HeadersSpec
import io.github.lcomment.korestdocs.spec.QueryParametersSpec
import io.github.lcomment.korestdocs.spec.RequestPartsSpec
import io.github.lcomment.korestdocs.type.RequestType
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActionsDsl
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
    val requestType = documentSpec.requestType ?: throw IllegalArgumentException("Not Exist request type")

    val mockMvc = MockMvcContextHolder.getContext().getMockMvc()
    val headersSpec = documentSpec.headersSpec
    val pathVariables = extractPathParameters(urlTemplate, documentSpec.pathVariablesSpec?.pathVariables)
    val queryParametersSpec = documentSpec.queryParametersSpec
    val requestFieldsSpec = documentSpec.requestFieldsSpec
    val requestPartsSpec = documentSpec.requestPartsSpec
    val requestPartFieldsSpec = documentSpec.requestPartFieldsSpec

    val resultActionsDsl = when (requestType) {
        RequestType.HTTP -> requestHttp(
            urlTemplate = urlTemplate,
            pathVariables = pathVariables,
            method = method,
            mockMvc = mockMvc,
            headersSpec = headersSpec,
            queryParameterSpec = queryParametersSpec,
            requestFieldsSpec = requestFieldsSpec,
        )

        RequestType.MULTIPART -> requestMultipart(
            urlTemplate = urlTemplate,
            pathVariables = pathVariables,
            method = method,
            mockMvc = mockMvc,
            headersSpec = headersSpec,
            queryParametersSpec = queryParametersSpec,
            requestPartFieldsSpec = requestPartFieldsSpec,
            requestPartsSpec = requestPartsSpec,
        )
    }

    return resultActionsDsl.andDo { handle(documentSpec.toResultHandler()) }
}

private fun requestHttp(
    urlTemplate: String,
    pathVariables: List<Any>,
    method: HttpMethod,
    mockMvc: MockMvc,
    headersSpec: HeadersSpec?,
    queryParameterSpec: QueryParametersSpec?,
    requestFieldsSpec: FieldsSpec?,
): ResultActionsDsl {
    return mockMvc.requestWithDocs(method, urlTemplate, *pathVariables.toTypedArray()) {
        queryParameterSpec?.queryParameters?.forEach { (key, value) ->
            param(key, value.toString())
        }

        headersSpec?.headers?.forEach { (key, value) ->
            header(key, value.toString())
        }

        content = toJsonString(requestFieldsSpec?.fields ?: emptyMap())
        contentType = MediaType.APPLICATION_JSON
    }
}

private fun requestMultipart(
    urlTemplate: String,
    pathVariables: List<Any>,
    method: HttpMethod,
    mockMvc: MockMvc,
    headersSpec: HeadersSpec?,
    queryParametersSpec: QueryParametersSpec?,
    requestPartFieldsSpec: Map<String, FieldsSpec>?,
    requestPartsSpec: RequestPartsSpec?,
): ResultActionsDsl {
    return mockMvc.multipartWithDocs(method, urlTemplate, *pathVariables.toTypedArray()) {
        queryParametersSpec?.queryParameters?.forEach { (key, value) ->
            param(key, value.toString())
        }

        headersSpec?.headers?.forEach { (key, value) ->
            header(key, value.toString())
        }

        requestPartsSpec?.parts?.values?.forEach {
            file(it)
        }

        requestPartFieldsSpec?.forEach { (key, value) ->
            file(value.fields.toMockMultipartFile(key))
        }

        contentType = MediaType.MULTIPART_FORM_DATA
        accept = MediaType.APPLICATION_JSON

        with {
            it.method = method.name()
            it
        }
    }
}

fun toJsonString(flatMap: Map<String, Any>): String {
    val nestedMap = mutableMapOf<String, Any>()

    val asMap = flatMap.keys
        .filter { it.contains('.') }
        .map { it.substringBefore('.') }
        .toSet()

    for ((key, value) in flatMap) {
        if (asMap.contains(key) && !key.contains('.')) {
            continue
        }

        insert(nestedMap, key, value)
    }

    return toJson(nestedMap)
}

fun insert(map: MutableMap<String, Any>, key: String, value: Any) {
    val dotIndex = key.indexOf('.')

    if (dotIndex == -1) {
        map[key] = value
    } else {
        val first = key.substring(0, dotIndex)
        val rest = key.substring(dotIndex + 1)
        val child = map.getOrPut(first) { mutableMapOf<String, Any>() } as MutableMap<String, Any>

        insert(child, rest, value)
    }
}

fun toJson(value: Any): String {
    return mapper.writeValueAsString(value)
}

private val mapper: ObjectMapper = ObjectMapper()
    .registerModule(JavaTimeModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
