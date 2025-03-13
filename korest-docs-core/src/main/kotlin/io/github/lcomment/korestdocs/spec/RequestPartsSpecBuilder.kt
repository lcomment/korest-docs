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

import io.github.lcomment.korestdocs.extension.toAttributes
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.request.RequestPartDescriptor
import org.springframework.restdocs.request.RequestPartsSnippet

internal class RequestPartsSpecBuilder(
    private val parts: MutableList<RequestPartDescriptor> = mutableListOf<RequestPartDescriptor>(),
) : RequestPartsSpec {

    override fun add(
        name: String,
        description: String?,
        optional: Boolean,
        ignored: Boolean,
        attributes: Map<String, Any?>,
    ) = add(
        RequestDocumentation.partWithName(name)
            .description(description)
            .apply {
                if (optional) optional()
                if (ignored) ignored()
            }
            .attributes(*attributes.toAttributes()),
    )

    override fun add(requestPartDescriptor: RequestPartDescriptor) {
        parts.add(requestPartDescriptor)
    }

    fun build(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
    ): RequestPartsSnippet =
        if (relaxed) {
            RequestDocumentation.relaxedRequestParts(attributes, parts)
        } else {
            RequestDocumentation.requestParts(attributes, parts)
        }
}
