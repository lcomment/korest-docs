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

package io.github.lcomment.korestdocs.extensions

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

internal class MapExtensionsTest : DescribeSpec({

    describe("toAttributes() Method Test") {

        context("if the map is not empty") {
            val attributeMap = mapOf("optional" to true, "ignored" to false)

            it("should return an array of attributes") {
                val attributes = attributeMap.toAttributes()

                attributes.size shouldBe 2
                attributes[0].key shouldBe "optional"
                attributes[0].value shouldBe true
                attributes[1].key shouldBe "ignored"
                attributes[1].value shouldBe false
            }
        }

        context("if the map is empty") {
            val attributeMap = mapOf<String, Any?>()

            it("should return an empty array") {
                val attributes = attributeMap.toAttributes()

                attributes.size shouldBe 0
            }
        }
    }

    describe("putFormat() Method Test") {

        context("if the type is EnumField") {
            val attributeMap = emptyMap<String, Any?>()

            it("should return the map with the format key") {
                val formattedMap = attributeMap.putFormat(ExampleEnum::class)
                println(formattedMap)
                formattedMap.size shouldBe 1
                formattedMap["format"] shouldBe listOf("A", "B", "C").joinToString()
            }
        }

        context("if the type is DateTimeField") {
            val attributeMap = emptyMap<String, Any?>()

            it("should return the map with the format key") {
                val formattedMap = attributeMap.putFormat(LocalDateTime::class)

                formattedMap.size shouldBe 1
                formattedMap["format"] shouldBe "yyyy-mm-ddThh:mm:ss"
            }
        }

        context("if the type is DateField") {
            val attributeMap = emptyMap<String, Any?>()

            it("should return the map with the format key") {
                val formattedMap = attributeMap.putFormat(LocalDate::class)

                formattedMap.size shouldBe 1
                formattedMap["format"] shouldBe "yyyy-mm-dd"
            }
        }

        context("if the type is TimeField") {
            val attributeMap = emptyMap<String, Any?>()

            it("should return the map with the format key") {
                val formattedMap = attributeMap.putFormat(LocalTime::class)

                formattedMap.size shouldBe 1
                formattedMap["format"] shouldBe "hh:mm:ss"
            }
        }

        context("if the type is not EnumField, DateTimeField, DateField, or TimeField") {
            val attributeMap = emptyMap<String, Any?>()

            it("should return the map without the format key") {
                val formattedMap = attributeMap.putFormat(String::class)

                formattedMap.size shouldBe 0
            }
        }
    }
})

internal enum class ExampleEnum {
    A, B, C,
}
