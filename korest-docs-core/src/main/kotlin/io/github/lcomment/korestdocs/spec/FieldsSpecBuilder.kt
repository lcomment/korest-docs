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
import io.github.lcomment.korestdocs.extensions.toAttributes
import io.github.lcomment.korestdocs.extensions.toFieldType
import kotlin.reflect.KClass
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.RequestPartFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet

class FieldsSpecBuilder(
    private val fieldDescriptors: MutableList<FieldDescriptor> = mutableListOf<FieldDescriptor>(),
) : FieldsSpec() {

    override fun <T : Any> field(
        path: String,
        description: String?,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any>,
    ) {
        fields.putIfAbsent(path, example)
        val descriptor = PayloadDocumentation.fieldWithPath(path)
            .type(type.toFieldType().toString())
            .description(description)
            .attributes(*attributes.putFormat(type).toAttributes())

        add(descriptor)
    }

    override fun <T : Any> subsectionField(
        path: String,
        description: String?,
        example: T,
        type: KClass<T>,
        attributes: Map<String, Any>,
    ) {
        val descriptor = PayloadDocumentation.subsectionWithPath(path)
            .description(description)
            .type(type)
            .attributes(*attributes.toAttributes())

        add(descriptor)
    }

    override fun add(fieldDescriptor: FieldDescriptor) {
        fieldDescriptors.add(fieldDescriptor)
    }

    override fun withPrefix(
        prefix: String,
        configure: FieldsSpec.() -> Unit,
    ) {
        val fields = FieldsSpecBuilder().apply(configure).fieldDescriptors
        PayloadDocumentation.applyPathPrefix(prefix, fields).forEach(::add)
    }

    fun buildRequestFields(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ): RequestFieldsSnippet =
        if (relaxed) {
            PayloadDocumentation.relaxedRequestFields(
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        } else {
            PayloadDocumentation.requestFields(
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        }

    fun buildResponseFields(
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ): ResponseFieldsSnippet =
        if (relaxed) {
            PayloadDocumentation.relaxedResponseFields(
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        } else {
            PayloadDocumentation.responseFields(
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        }

    fun buildRequestPartFields(
        part: String,
        relaxed: Boolean,
        subsectionExtractor: PayloadSubsectionExtractor<*>?,
        attributes: Map<String, Any?>,
    ): RequestPartFieldsSnippet =
        if (relaxed) {
            PayloadDocumentation.relaxedRequestPartFields(
                part,
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        } else {
            PayloadDocumentation.requestPartFields(
                part,
                subsectionExtractor,
                attributes,
                fieldDescriptors,
            )
        }
}
