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
import org.springframework.mock.web.MockMultipartFile

class RequestPartsSpecTest : DescribeSpec({

    describe("requestPart() Method Test") {
        val requestPartsSpec = RequestPartsSpecBuilder()

        context("if add request part") {
            val example = MockMultipartFile("image", "", "image/png", "image".toByteArray())

            it("requestPartsSpec should have one request part") {
                requestPartsSpec.part(
                    name = "test",
                    description = "test",
                    part = example,
                )

                requestPartsSpec.parts.size shouldBe 1
                requestPartsSpec.parts["test"] shouldBe example
            }
        }
    }

    describe("optionalRequestPart() Method Test") {
        val requestPartsSpec = RequestPartsSpecBuilder()

        context("if add request part") {
            val example = MockMultipartFile("image", "", "image/png", "image".toByteArray())

            it("requestPartsSpec should have one request part") {
                requestPartsSpec.optionalPart(
                    name = "test",
                    description = "test",
                    part = example,
                )

                requestPartsSpec.parts.size shouldBe 1
                requestPartsSpec.parts["test"] shouldBe example
            }
        }
    }
})
