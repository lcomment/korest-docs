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

import io.github.lcomment.korestdocs.spec.FieldsSpec
import io.github.lcomment.korestdocs.spec.HeadersSpec
import io.github.lcomment.korestdocs.spec.PathVariablesSpec
import io.github.lcomment.korestdocs.spec.QueryParametersSpec
import io.github.lcomment.korestdocs.spec.RequestPartsSpec
import io.github.lcomment.korestdocs.type.RequestType
import org.springframework.http.HttpMethod
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Snippet

interface MockMvcDocumentGenerator {

    val identifier: String

    val requestPreprocessor: OperationRequestPreprocessor

    val responsePreprocessor: OperationResponsePreprocessor

    val snippets: List<Snippet>

    var requestType: RequestType?

    var method: HttpMethod?

    var urlTemplate: String?

    var headersSpec: HeadersSpec?

    var pathVariablesSpec: PathVariablesSpec?

    var queryParametersSpec: QueryParametersSpec?

    var requestFieldsSpec: FieldsSpec?

    var requestPartsSpec: RequestPartsSpec?

    var requestPartFieldsSpec: Map<String, FieldsSpec>?

    fun addSnippet(snippet: Snippet)

    fun request(
        method: HttpMethod,
        urlTemplate: String,
        configure: PathVariablesSpec.() -> Unit = {},
    )

    fun multipart(
        method: HttpMethod,
        urlTemplate: String,
        configure: PathVariablesSpec.() -> Unit = {},
    )

    fun requestHeader(
        attributes: Map<String, Any?> = emptyMap(),
        configure: HeadersSpec.() -> Unit,
    )

    fun pathParameter(
        relaxed: Boolean = false,
        attributes: Map<String, Any?> = emptyMap(),
        configure: PathVariablesSpec.() -> Unit,
    )

    fun requestParameter(
        relaxed: Boolean = false,
        attributes: Map<String, Any?> = emptyMap(),
        configure: QueryParametersSpec.() -> Unit,
    )

    fun requestField(
        relaxed: Boolean = false,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: FieldsSpec.() -> Unit,
    )

    fun requestPart(
        relaxed: Boolean = false,
        attributes: Map<String, Any?> = emptyMap(),
        configure: RequestPartsSpec.() -> Unit,
    )

    fun requestPartField(
        part: String,
        relaxed: Boolean = false,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: FieldsSpec.() -> Unit,
    )

    fun responseHeader(
        attributes: Map<String, Any?> = emptyMap(),
        configure: HeadersSpec.() -> Unit,
    )

    fun responseField(
        relaxed: Boolean = false,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: FieldsSpec.() -> Unit,
    )
}
