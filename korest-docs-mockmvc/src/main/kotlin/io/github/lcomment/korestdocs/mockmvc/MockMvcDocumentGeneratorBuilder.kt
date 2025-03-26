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
import io.github.lcomment.korestdocs.spec.FieldsSpecBuilder
import io.github.lcomment.korestdocs.spec.HeadersSpec
import io.github.lcomment.korestdocs.spec.HeadersSpecBuilder
import io.github.lcomment.korestdocs.spec.ParametersSpec
import io.github.lcomment.korestdocs.spec.ParametersSpecBuilder
import io.github.lcomment.korestdocs.spec.RequestPartsSpec
import io.github.lcomment.korestdocs.spec.RequestPartsSpecBuilder
import org.springframework.http.HttpMethod
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Snippet

internal class MockMvcDocumentGeneratorBuilder(
    override val identifier: String,
    override var method: HttpMethod? = null,
    override var urlTemplate: String? = null,
    override var headersBuilder: HeadersSpec? = null,
    override var parametersBuilder: ParametersSpec? = null,
    override var requestFieldsBuilder: FieldsSpec? = null,
    override val requestPreprocessor: OperationRequestPreprocessor = OperationRequestPreprocessor { r -> r },
    override val responsePreprocessor: OperationResponsePreprocessor = OperationResponsePreprocessor { r -> r },
    override val snippets: MutableList<Snippet> = mutableListOf<Snippet>(),
) : MockMvcDocumentGenerator {

    override fun addSnippet(snippet: Snippet) {
        snippets.add(snippet)
    }

    override fun request(
        method: HttpMethod,
        urlTemplate: String,
        configure: ParametersSpec.() -> Unit,
    ) {
        this.method = method
        this.urlTemplate = urlTemplate
        this.parametersBuilder = ParametersSpecBuilder().apply(configure)
    }

    override fun requestHeader(
        attributes: Map<String, Any?>,
        configure: HeadersSpec.() -> Unit,
    ) {
        val specBuilder = HeadersSpecBuilder().apply(configure)

        this.headersBuilder = specBuilder
        addSnippet(specBuilder.buildRequestHeaders(attributes))
    }

    override fun pathParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: ParametersSpec.() -> Unit,
    ) {
        val specBuilder = ParametersSpecBuilder().apply(configure)

        addSnippet(specBuilder.buildPathParameters(relaxed, attributes))
    }

    override fun requestParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: ParametersSpec.() -> Unit,
    ) {
        val specBuilder = ParametersSpecBuilder().apply(configure)

        addSnippet(specBuilder.buildQueryParameters(relaxed, attributes))
    }

    override fun requestField(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val specBuilder = FieldsSpecBuilder().apply(configure)
        this.requestFieldsBuilder = specBuilder

        addSnippet(specBuilder.buildRequestFields(relaxed, subsectionExtractor, attributes))
    }

    override fun requestPart(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: RequestPartsSpec.() -> Unit,
    ) {
        val snippet = RequestPartsSpecBuilder().apply(configure)
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

    override fun responseHeader(
        attributes: Map<String, Any?>,
        configure: HeadersSpec.() -> Unit,
    ) {
        val snippet = HeadersSpecBuilder()
            .apply(configure)
            .buildResponseHeaders(attributes)

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
}

fun documentationScope(identifier: String): MockMvcDocumentGenerator = MockMvcDocumentGeneratorBuilder(identifier)
