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

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

internal class HeadersSpecTest : DescribeSpec({

    describe("header() Method Test") {
        val headersSpec = HeadersSpecBuilder()

        context("if add header") {
            val example = "test"

            it("fieldsSpec should have one field") {
                headersSpec.header(
                    name = "test",
                    description = "test",
                    example = example,
                )

                headersSpec.headers.size shouldBe 1
                headersSpec.headers["test"] shouldBe example
            }
        }
    }

    describe("optionalHeader() Method Test") {
        val headersSpec = HeadersSpecBuilder()

        context("if add header") {
            val example = "test"

            it("headersSpec should have one header") {
                headersSpec.header(
                    name = "test",
                    description = "test",
                    example = example,
                )

                headersSpec.headers.size shouldBe 1
                headersSpec.headers["test"] shouldBe example
            }
        }
    }
})
