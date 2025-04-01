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

internal class FieldsSpecTest : DescribeSpec({

    describe("field() Method Test") {
        val fieldsSpec = FieldsSpecBuilder()

        context("if add field") {
            val example = "test"

            it("fieldsSpec should have one field") {
                fieldsSpec.field(
                    path = "test",
                    description = "test",
                    example = example,
                )

                fieldsSpec.fields.size shouldBe 1
                fieldsSpec.fields["test"] shouldBe example
            }
        }
    }

    describe("optionalField() Method Test") {
        val fieldsSpec = FieldsSpecBuilder()

        context("if add field") {
            val example = "test"

            it("fieldsSpec should have one field") {
                fieldsSpec.optionalField(
                    path = "test",
                    description = "test",
                    example = example,
                )

                fieldsSpec.fields.size shouldBe 1
                fieldsSpec.fields["test"] shouldBe example
            }
        }
    }

    describe("ignoredField() Method Test") {
        val fieldsSpec = FieldsSpecBuilder()

        context("if add field") {
            val example = "test"

            it("fieldsSpec should have one field") {
                fieldsSpec.ignoredField(
                    path = "test",
                    description = "test",
                    example = example,
                )

                fieldsSpec.fields.size shouldBe 1
                fieldsSpec.fields["test"] shouldBe example
            }
        }
    }

    describe("subsectionField() Method Test") {
        val fieldsSpec = FieldsSpecBuilder()

        context("if add field") {
            val example = "test"

            it("fieldsSpec should have one field") {
                fieldsSpec.subsectionField(
                    path = "test",
                    description = "test",
                    example = example,
                )

                fieldsSpec.fields.size shouldBe 0
            }
        }
    }
})
