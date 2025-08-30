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

package io.github.lcomment.example.dto.request

import io.github.lcomment.example.dto.response.ArrayResponse
import io.github.lcomment.example.enums.ExampleEnum
import jakarta.validation.constraints.NotBlank

data class ExampleRequest(
    @field:NotBlank
    val example: String? = null,
    val arrayData: ArrayResponse = ArrayResponse(),
    val enumData: ExampleEnum = ExampleEnum.EXAMPLE1,
    val objectData: ObjectExample = ObjectExample(),
) {

    data class ObjectExample(
        val value1: String = "value1",
        val value2: Int = 1,
        val value3: Boolean = true,
    )
}
