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
import org.springframework.restdocs.payload.FieldDescriptor

@RestdocsSpecDslMarker
abstract class FieldsSpec : DocumentSpec {

    val fields = mutableMapOf<String, Any>()

    inline fun <reified T : Any> field(
        path: String,
        description: String?,
        example: T,
        attributes: Map<String, Any> = mapOf("optional" to false, "ignored" to false),
    ) {
        this.field(path, description, example, T::class, attributes)
    }

    inline fun <reified T : Any> optionalField(
        path: String,
        description: String?,
        example: T,
        attributes: Map<String, Any> = mapOf("optional" to true, "ignored" to false),
    ) {
        this.field(path, description, example, T::class, attributes)
    }

    inline fun <reified T : Any> subsectionField(
        path: String,
        description: String?,
        example: T,
        attributes: Map<String, Any> = mapOf("optional" to false, "ignored" to false),
    ) {
        this.subsectionField(path, description, example, T::class, attributes)
    }

    abstract fun <T : Any> field(
        path: String,
        description: String? = null,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any>,
    )

    abstract fun ignoredField(path: String)

    abstract fun <T : Any> subsectionField(
        path: String,
        description: String?,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any>,
    )

    abstract fun add(fieldDescriptor: FieldDescriptor)

    abstract fun withPrefix(prefix: String, configure: FieldsSpec.() -> Unit)
}

