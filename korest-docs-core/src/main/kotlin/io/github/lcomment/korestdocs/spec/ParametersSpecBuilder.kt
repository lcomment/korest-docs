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

import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.PathParametersSnippet
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation

internal class ParametersSpecBuilder(
    private val parameters: MutableList<ParameterDescriptor> = mutableListOf<ParameterDescriptor>(),
) : ParametersSpec {

    override fun add(
        name: String,
        description: String?,
        optional: Boolean,
        ignored: Boolean,
        attributes: Map<String, Any?>,
    ) = add(
        RequestDocumentation.parameterWithName(name)
            .description(description)
            .apply {
                if (optional) optional()
                if (ignored) ignored()
            }
            .attributes(*attributes.toAttributes()),
    )

    override fun add(parameterDescriptor: ParameterDescriptor) {
        parameters.add(parameterDescriptor)
    }

    fun buildPathParameters(
        relaxed: Boolean,
        attributes: Map<String, Any?>,
    ): PathParametersSnippet =
        if (relaxed) {
            RequestDocumentation.relaxedPathParameters(attributes, parameters)
        } else {
            RequestDocumentation.pathParameters(attributes, parameters)
        }

    fun buildQueryParameters(relaxed: Boolean, attributes: Map<String, Any?>): QueryParametersSnippet =
        if (relaxed) {
            RequestDocumentation.relaxedQueryParameters(attributes, parameters)
        } else {
            RequestDocumentation.queryParameters(attributes, parameters)
        }
}
