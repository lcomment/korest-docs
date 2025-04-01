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

import io.github.lcomment.korestdocs.type.AnyField
import io.github.lcomment.korestdocs.type.ArrayField
import io.github.lcomment.korestdocs.type.BooleanField
import io.github.lcomment.korestdocs.type.DateField
import io.github.lcomment.korestdocs.type.DateTimeField
import io.github.lcomment.korestdocs.type.EnumField
import io.github.lcomment.korestdocs.type.NumberField
import io.github.lcomment.korestdocs.type.ObjectField
import io.github.lcomment.korestdocs.type.StringField
import io.github.lcomment.korestdocs.type.TimeField
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Date

internal class KClassExtensionsTest : DescribeSpec({

    describe("toFieldType() Test") {

        context("if the class is String") {
            val fieldType = String::class.toFieldType()

            it("should return StringField") {
                fieldType shouldBe StringField
            }
        }

        context("if the class is Int") {
            val fieldType = Int::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Long") {
            val fieldType = Long::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Short") {
            val fieldType = Short::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Byte") {
            val fieldType = Byte::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Double") {
            val fieldType = Double::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Float") {
            val fieldType = Float::class.toFieldType()

            it("should return NumberField") {
                fieldType shouldBe NumberField
            }
        }

        context("if the class is Boolean") {
            val fieldType = Boolean::class.toFieldType()

            it("should return BooleanField") {
                fieldType shouldBe BooleanField
            }
        }

        context("if the class is List") {
            val fieldType = List::class.toFieldType()

            it("should return ArrayField") {
                fieldType shouldBe ArrayField
            }
        }

        context("if the class is Set") {
            val fieldType = Set::class.toFieldType()

            it("should return ArrayField") {
                fieldType shouldBe ArrayField
            }
        }

        context("if the class is Map") {
            val fieldType = Map::class.toFieldType()

            it("should return ObjectField") {
                fieldType shouldBe ObjectField
            }
        }

        context("if the class is Any") {
            val fieldType = Any::class.toFieldType()

            it("should return AnyField") {
                fieldType shouldBe AnyField
            }
        }

        context("if the class is Enum") {
            val fieldType = ExampleEnum::class.toFieldType()

            it("should return EnumField") {
                fieldType shouldBe EnumField
            }
        }

        context("if the class is LocalDate") {
            val fieldType = LocalDate::class.toFieldType()

            it("should return DateField") {
                fieldType shouldBe DateField
            }
        }

        context("if the class is LocalTime") {
            val fieldType = LocalTime::class.toFieldType()

            it("should return DateField") {
                fieldType shouldBe TimeField
            }
        }

        context("if the class is LocalDateTime") {
            val fieldType = LocalDateTime::class.toFieldType()

            it("should return DateTimeField") {
                fieldType shouldBe DateTimeField
            }
        }

        context("if the class is ZonedDateTime") {
            val fieldType = ZonedDateTime::class.toFieldType()

            it("should return DateTimeField") {
                fieldType shouldBe DateTimeField
            }
        }

        context("if the class is OffsetDateTime") {
            val fieldType = OffsetDateTime::class.toFieldType()

            it("should return DateTimeField") {
                fieldType shouldBe DateTimeField
            }
        }

        context("if the class is Date") {
            val fieldType = Date::class.toFieldType()

            it("should return DateTimeField") {
                fieldType shouldBe DateTimeField
            }
        }

        context("if the class is Calendar") {
            val fieldType = Calendar::class.toFieldType()

            it("should return DateTimeField") {
                fieldType shouldBe DateTimeField
            }
        }
    }
})
