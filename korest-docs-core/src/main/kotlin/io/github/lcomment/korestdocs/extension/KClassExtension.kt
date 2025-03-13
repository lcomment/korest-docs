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

import io.github.lcomment.korestdocs.type.AnyField
import io.github.lcomment.korestdocs.type.ArrayField
import io.github.lcomment.korestdocs.type.BooleanField
import io.github.lcomment.korestdocs.type.DateField
import io.github.lcomment.korestdocs.type.DateTimeField
import io.github.lcomment.korestdocs.type.EnumField
import io.github.lcomment.korestdocs.type.FieldType
import io.github.lcomment.korestdocs.type.NumberField
import io.github.lcomment.korestdocs.type.ObjectField
import io.github.lcomment.korestdocs.type.StringField
import io.github.lcomment.korestdocs.type.TimeField
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Date
import kotlin.reflect.KClass

internal fun KClass<*>.toFieldType(): FieldType = when {
    this == String::class -> StringField
    this == Int::class || this == Long::class || this == Short::class || this == Byte::class -> NumberField
    this == Double::class || this == Float::class -> NumberField
    this == Boolean::class -> BooleanField
    this == List::class || this == Set::class -> ArrayField
    this == Map::class -> ObjectField
    this == Any::class -> AnyField
    this.java.superclass == Enum::class.java -> EnumField
    this == LocalDate::class -> DateField
    this == LocalTime::class -> TimeField
    this == LocalDateTime::class || this == ZonedDateTime::class || this == OffsetDateTime::class -> DateTimeField
    this == Date::class || this == Calendar::class -> DateTimeField
    else -> ObjectField
}

internal fun <T : Enum<T>> KClass<*>.entryNames(separator: String = ", "): String {
    return this.java.enumConstants.joinToString(separator)
}
