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

package io.github.lcomment.korestdocs.extension

import io.github.lcomment.korestdocs.type.DateField
import io.github.lcomment.korestdocs.type.DateTimeField
import io.github.lcomment.korestdocs.type.EnumField
import io.github.lcomment.korestdocs.type.TimeField
import kotlin.reflect.KClass
import org.springframework.restdocs.snippet.Attributes

internal fun Map<String, Any?>.toAttributes(): Array<Attributes.Attribute> {
    return map { Attributes.key(it.key).value(it.value) }.toTypedArray()
}

internal fun Map<String, Any?>.putFormat(
    attributes: Map<String, Any?>,
    type: KClass<*>,
): Map<String, Any?> {
    val fieldType = type.toFieldType()
    val mutableAttributes = attributes.toMutableMap()

    when (fieldType) {
        EnumField -> mutableAttributes["format"] = type.entryNames()
        DateTimeField -> mutableAttributes["format"] = "yyyy-mm-ddThh:mm:ss"
        DateField -> mutableAttributes["format"] = "yyyy-mm-dd"
        TimeField -> mutableAttributes["format"] = "hh:mm:ss"
        else -> {
            // Do nothing
        }
    }

    return mutableAttributes
}
