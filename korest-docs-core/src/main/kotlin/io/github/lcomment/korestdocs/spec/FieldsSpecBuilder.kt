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

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadSubsectionExtractor
import org.springframework.restdocs.payload.RequestFieldsSnippet
import org.springframework.restdocs.payload.RequestPartFieldsSnippet
import org.springframework.restdocs.payload.ResponseFieldsSnippet

internal class FieldsSpecBuilder(
    private val fields: MutableList<FieldDescriptor> = mutableListOf<FieldDescriptor>(),
) : FieldsSpec {

    override fun add(
        path: String,
        type: Any?,
        description: String?,
        optional: Boolean,
        ignored: Boolean,
        attributes: Map<String, Any?>,
    ) = add(
        PayloadDocumentation.fieldWithPath(path)
            .type(type)
            .description(description)
            .apply {
                if (optional) optional()
                if (ignored) ignored()
            }
            .attributes(*attributes.toAttributes()),
    )

    override fun addSubsection(
        path: String,
        type: Any?,
        description: String?,
        optional: Boolean,
        ignored: Boolean,
        attributes: Map<String, Any?>,
    ) {
        add(
            PayloadDocumentation.subsectionWithPath(path)
                .description(description)
                .type(type)
                .apply {
                    if (optional) optional()
                    if (ignored) ignored()
                }
                .attributes(*attributes.toAttributes()),
        )
    }

    override fun add(fieldDescriptor: FieldDescriptor) {
        fields.add(fieldDescriptor)
    }

    override fun withPrefix(
        prefix: String,
        configure: FieldsSpec.() -> Unit,
    ) {
        val fields = FieldsSpecBuilder().apply(configure).fields
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
                fields,
            )
        } else {
            PayloadDocumentation.requestFields(
                subsectionExtractor,
                attributes,
                fields,
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
                fields,
            )
        } else {
            PayloadDocumentation.responseFields(
                subsectionExtractor,
                attributes,
                fields,
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
                fields,
            )
        } else {
            PayloadDocumentation.requestPartFields(
                part,
                subsectionExtractor,
                attributes,
                fields,
            )
        }
}
