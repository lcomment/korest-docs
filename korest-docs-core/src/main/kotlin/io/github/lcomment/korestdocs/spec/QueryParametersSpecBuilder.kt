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

import io.github.lcomment.korestdocs.extensions.putFormat
import io.github.lcomment.korestdocs.extensions.putType
import io.github.lcomment.korestdocs.extensions.toAttributes
import kotlin.reflect.KClass
import org.springframework.restdocs.request.ParameterDescriptor
import org.springframework.restdocs.request.QueryParametersSnippet
import org.springframework.restdocs.request.RequestDocumentation

class QueryParametersSpecBuilder(
    private val parameters: MutableList<ParameterDescriptor> = mutableListOf<ParameterDescriptor>(),
) : QueryParametersSpec() {

    override fun <T : Any> add(
        name: String,
        description: String?,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any?>,
    ) {
        val descriptor = RequestDocumentation.parameterWithName(name)
            .description(description)
            .attributes(*attributes.putFormat(type).putType(type).toAttributes())

        add(descriptor)
    }

    override fun add(parameterDescriptor: ParameterDescriptor) {
        parameters.add(parameterDescriptor)
    }

    fun buildQueryParameters(relaxed: Boolean, attributes: Map<String, Any?>): QueryParametersSnippet {
        return if (relaxed) {
            RequestDocumentation.relaxedQueryParameters(attributes, parameters)
        } else {
            RequestDocumentation.queryParameters(attributes, parameters)
        }
    }
}
