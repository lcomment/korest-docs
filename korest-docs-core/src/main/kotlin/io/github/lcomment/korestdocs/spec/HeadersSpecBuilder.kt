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

import org.springframework.restdocs.headers.HeaderDescriptor
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.RequestHeadersSnippet
import org.springframework.restdocs.headers.ResponseHeadersSnippet

internal class HeadersSpecBuilder(
    private val headers: MutableList<HeaderDescriptor> = mutableListOf<HeaderDescriptor>(),
) : HeadersSpec {

    override fun add(
        name: String,
        description: String,
        optional: Boolean,
        attributes: Map<String, Any?>,
    ) = add(
        HeaderDocumentation.headerWithName(name)
            .description(description)
            .apply {
                if (optional) optional()
            }
            .attributes(*attributes.toAttributes()),
    )

    override fun add(headerDescriptor: HeaderDescriptor) {
        headers.add(headerDescriptor)
    }

    fun buildRequestHeaders(attributes: Map<String, Any?>): RequestHeadersSnippet =
        HeaderDocumentation.requestHeaders(attributes, headers)

    fun buildResponseHeaders(attributes: Map<String, Any?>): ResponseHeadersSnippet =
        HeaderDocumentation.responseHeaders(attributes, headers)
}
