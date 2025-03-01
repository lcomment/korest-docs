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

package io.github.lcomment.korestdocs

import org.springframework.restdocs.payload.JsonFieldType

sealed class FieldType(
    val type: JsonFieldType,
)

object ARRAY : FieldType(JsonFieldType.ARRAY)
object BOOLEAN : FieldType(JsonFieldType.BOOLEAN)
object OBJECT : FieldType(JsonFieldType.OBJECT)
object NUMBER : FieldType(JsonFieldType.NUMBER)
object NULL : FieldType(JsonFieldType.NULL)
object STRING : FieldType(JsonFieldType.STRING)
object DATETIME : FieldType(JsonFieldType.STRING)
object DATE : FieldType(JsonFieldType.STRING)
object TIME : FieldType(JsonFieldType.STRING)
object ANY : FieldType(JsonFieldType.VARIES)
