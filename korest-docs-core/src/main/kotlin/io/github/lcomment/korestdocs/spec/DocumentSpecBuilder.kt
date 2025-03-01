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

import org.springframework.restdocs.hypermedia.LinkExtractor
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Snippet

internal class DocumentSpecBuilder(
    override val identifier: String,
    override val requestPreprocessor: OperationRequestPreprocessor = OperationRequestPreprocessor { r -> r },
    override val responsePreprocessor: OperationResponsePreprocessor = OperationResponsePreprocessor { r -> r },
    override val snippets: MutableList<Snippet> = mutableListOf<Snippet>(),
) : DocumentSpec {

    override fun addSnippet(snippet: Snippet) {
        snippets.add(snippet)
    }

    override fun pathParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: ParametersSpec.() -> Unit,
    ) {
        val snippet = ParametersSpecBuilder().apply(configure).buildPathParameters(relaxed, attributes)

        addSnippet(snippet)
    }

    override fun requestParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: ParametersSpec.() -> Unit,
    ) {
        val snippet = ParametersSpecBuilder()
            .apply(configure)
            .buildQueryParameters(relaxed, attributes)

        addSnippet(snippet)
    }

    override fun requestField(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val snippet = FieldsSpecBuilder()
            .apply(configure)
            .buildRequestFields(relaxed, subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun responseField(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val snippet = FieldsSpecBuilder()
            .apply(configure)
            .buildResponseFields(relaxed, subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun requestPart(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: RequestPartsSpec.() -> Unit,
    ) {
        val snippet = RequestPartsSpecBuilder()
            .apply(configure)
            .build(relaxed, attributes)

        addSnippet(snippet)
    }

    override fun requestPartField(
        part: String,
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val snippet = FieldsSpecBuilder()
            .apply(configure)
            .buildRequestPartFields(part, relaxed, subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun requestBody(
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ) {
        val snippet = PayloadDocumentation.requestBody(subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun responseBody(
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ) {
        val snippet = PayloadDocumentation.responseBody(subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun requestPartBody(
        part: String,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ) {
        val snippet = PayloadDocumentation.requestPartBody(part, subsectionExtractor, attributes)

        addSnippet(snippet)
    }

    override fun requestHeader(
        attributes: Map<String, Any?>,
        configure: HeadersSpec.() -> Unit,
    ) {
        val snippet = HeadersSpecBuilder()
            .apply(configure)
            .buildRequestHeaders(attributes)

        addSnippet(snippet)
    }

    override fun responseHeader(
        attributes: Map<String, Any?>,
        configure: HeadersSpec.() -> Unit,
    ) {
        val snippet = HeadersSpecBuilder()
            .apply(configure)
            .buildResponseHeaders(attributes)

        addSnippet(snippet)
    }

    override fun link(
        relaxed: Boolean,
        linkExtractor: LinkExtractor?,
        attributes: Map<String, Any?>,
        configure: LinksSpec.() -> Unit,
    ) {
        val snippet = LinksSpecBuilder()
            .apply(configure)
            .build(relaxed, linkExtractor, attributes)

        addSnippet(snippet)
    }
}

fun documentationScope(identifier: String): DocumentSpec = DocumentSpecBuilder(identifier)
