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

package io.github.lcomment.korestdocs.spec

import io.github.lcomment.korestdocs.annotation.RestdocsSpecDslMarker
import org.springframework.restdocs.hypermedia.LinkExtractor
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Snippet

@RestdocsSpecDslMarker
interface DocumentSpec {

    val identifier: String

    val requestPreprocessor: OperationRequestPreprocessor

    val responsePreprocessor: OperationResponsePreprocessor

    val snippets: List<Snippet>

    fun addSnippet(snippet: Snippet)

    fun pathParameter(
        relaxed: Boolean = false,
        attributes: Map<String, Any?> = emptyMap(),
        configure: ParametersSpec.() -> Unit,
    )

    fun requestHeader(
        attributes: Map<String, Any?> = emptyMap(),
        configure: HeadersSpec.() -> Unit,
    )

    fun requestParameter(
        relaxed: Boolean = false,
        attributes: Map<String, Any?> = emptyMap(),
        configure: ParametersSpec.() -> Unit,
    )

    fun requestField(
        relaxed: Boolean = false,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: FieldsSpec.() -> Unit,
    )

    fun responseField(
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

    fun requestPartBody(
        part: String,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
    )

    fun requestPartField(
        part: String,
        relaxed: Boolean = false,
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: FieldsSpec.() -> Unit,
    )

    fun requestBody(
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
    )

    fun responseHeader(
        attributes: Map<String, Any?> = emptyMap(),
        configure: HeadersSpec.() -> Unit,
    )

    fun responseBody(
        subsectionExtractor: PayloadSubsectionExtractor<*>? = null,
        attributes: Map<String, Any?> = emptyMap(),
    )

    fun link(
        relaxed: Boolean = false,
        linkExtractor: LinkExtractor? = null,
        attributes: Map<String, Any?> = emptyMap(),
        configure: LinksSpec.() -> Unit,
    )
}
