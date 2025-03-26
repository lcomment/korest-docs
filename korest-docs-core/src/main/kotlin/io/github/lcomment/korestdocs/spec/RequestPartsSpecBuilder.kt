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

import io.github.lcomment.korestdocs.extensions.toAttributes
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestPartDescriptor
import org.springframework.restdocs.request.RequestPartsSnippet

class RequestPartsSpecBuilder(
    private val partDescriptors: MutableList<RequestPartDescriptor> = mutableListOf<RequestPartDescriptor>(),
) : RequestPartsSpec() {

    override fun part(
        name: String,
        description: String?,
        part: MockMultipartFile,
        attributes: Map<String, Any>,
    ) {
        parts.putIfAbsent(name, part)
        val descriptor = RequestDocumentation.partWithName(name)
            .description(description)
            .attributes(*attributes.toAttributes())

        add(descriptor)
    }

    override fun optionalPart(
        name: String,
        description: String?,
        part: MockMultipartFile,
        attributes: Map<String, Any>,
    ) {
        parts.putIfAbsent(name, part)
        val descriptor = RequestDocumentation.partWithName(name)
            .description(description)
            .attributes(*attributes.toAttributes())

        add(descriptor)
    }

    override fun add(requestPartDescriptor: RequestPartDescriptor) {
        partDescriptors.add(requestPartDescriptor)
    }

    fun build(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
    ): RequestPartsSnippet =
        if (relaxed) {
            RequestDocumentation.relaxedRequestParts(attributes, partDescriptors)
        } else {
            RequestDocumentation.requestParts(attributes, partDescriptors)
        }
}
