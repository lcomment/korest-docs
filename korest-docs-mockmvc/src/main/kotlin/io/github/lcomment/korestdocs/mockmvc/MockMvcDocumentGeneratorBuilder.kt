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
import io.github.lcomment.korestdocs.spec.PathVariablesSpec
import io.github.lcomment.korestdocs.spec.PathVariablesSpecBuilder
import io.github.lcomment.korestdocs.spec.QueryParametersSpec
import io.github.lcomment.korestdocs.spec.QueryParametersSpecBuilder
import io.github.lcomment.korestdocs.spec.RequestPartsSpec
import io.github.lcomment.korestdocs.spec.RequestPartsSpecBuilder
import io.github.lcomment.korestdocs.type.RequestType
import org.springframework.http.HttpMethod
import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.snippet.Snippet

internal class MockMvcDocumentGeneratorBuilder(
    override val identifier: String,
    override var method: HttpMethod? = null,
    override var urlTemplate: String? = null,
    override var requestType: RequestType? = null,
    override var headersSpec: HeadersSpec? = null,
    override var pathVariablesSpec: PathVariablesSpec? = null,
    override var queryParametersSpec: QueryParametersSpec? = null,
    override var requestFieldsSpec: FieldsSpec? = null,
    override var requestPartsSpec: RequestPartsSpec? = null,
    override var requestPartFieldsSpec: Map<String, FieldsSpec>? = null,
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
        configure: PathVariablesSpec.() -> Unit,
    ) {
        this.method = method
        this.urlTemplate = urlTemplate
        this.requestType = RequestType.HTTP

        val specBuilder = PathVariablesSpecBuilder().apply(configure)
        this.pathVariablesSpec = specBuilder
        addSnippet(specBuilder.buildPathParameters(false, emptyMap()))
    }

    override fun multipart(
        method: HttpMethod,
        urlTemplate: String,
        configure: PathVariablesSpec.() -> Unit,
    ) {
        this.method = method
        this.urlTemplate = urlTemplate
        this.requestType = RequestType.MULTIPART

        val specBuilder = PathVariablesSpecBuilder().apply(configure)
        this.pathVariablesSpec = specBuilder
        addSnippet(specBuilder.buildPathParameters(false, emptyMap()))
    }

    override fun requestHeader(
        attributes: Map<String, Any?>,
        configure: HeadersSpec.() -> Unit,
    ) {
        val spec = HeadersSpecBuilder().apply(configure)

        this.headersSpec = spec
        addSnippet(spec.buildRequestHeaders(attributes))
    }

    override fun pathParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: PathVariablesSpec.() -> Unit,
    ) {
        val spec = PathVariablesSpecBuilder().apply(configure)

        addSnippet(spec.buildPathParameters(relaxed, attributes))
    }

    override fun requestParameter(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: QueryParametersSpec.() -> Unit,
    ) {
        val spec = QueryParametersSpecBuilder().apply(configure)
        this.queryParametersSpec = spec

        addSnippet(spec.buildQueryParameters(relaxed, attributes))
    }

    override fun requestField(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val spec = FieldsSpecBuilder().apply(configure)
        this.requestFieldsSpec = spec

        addSnippet(spec.buildRequestFields(relaxed, subsectionExtractor, attributes))
    }

    override fun requestPart(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
        configure: RequestPartsSpec.() -> Unit,
    ) {
        val spec = RequestPartsSpecBuilder().apply(configure)
        this.requestPartsSpec = spec

        addSnippet(spec.build(relaxed, attributes))
    }

    override fun requestPartField(
        part: String,
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
        configure: FieldsSpec.() -> Unit,
    ) {
        val spec = FieldsSpecBuilder().apply(configure)
        this.requestPartFieldsSpec = mapOf(part to spec)

        addSnippet(spec.buildRequestPartFields(part, relaxed, subsectionExtractor, attributes))
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
