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
import kotlin.reflect.KClass
import org.springframework.restdocs.request.ParameterDescriptor

@RestdocsSpecDslMarker
abstract class PathVariablesSpec : ParametersSpec {

    val pathVariables: MutableMap<String, Any> = mutableMapOf()
    val queryParameters: MutableMap<String, Any> = mutableMapOf()

    inline fun <reified T : Any> pathVariable(
        name: String,
        description: String? = null,
        example: T,
        attributes: Map<String, Any?> = emptyMap(),
    ) {
        pathVariables.putIfAbsent(name, example)
        add(name, description, example, T::class, attributes)
    }

    abstract fun <T : Any> add(
        name: String,
        description: String? = null,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any?> = emptyMap(),
    )

    abstract fun add(parameterDescriptor: ParameterDescriptor)
}
