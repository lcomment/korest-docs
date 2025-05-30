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

package io.github.lcomment.korestdocs.mockmvc.extensions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class StringExtensionsTest : DescribeSpec({

    describe("extractPathParameters() Function Test") {

        it("should return empty list when pathVariables is null or empty") {
            val urlTemplate = "/api/v1/users/{id}"
            val pathVariables = emptyMap<String, Any>()

            val result = extractPathParameters(urlTemplate, pathVariables)

            result shouldBe emptyList()
        }

        it("should return list of path variables") {
            val urlTemplate = "/api/v1/users/{id}"
            val pathVariables = mapOf("id" to 1)

            val result = extractPathParameters(urlTemplate, pathVariables)

            result shouldBe listOf(1)
        }
    }
})
