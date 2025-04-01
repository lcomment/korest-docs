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

package io.github.lcomment.korestdocs.mockmvc

import io.github.lcomment.korestdocs.type.RequestType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.http.HttpMethod

internal class MockMvcDocumentGeneratorTest : DescribeSpec({

    describe("request() and multipart()") {
        val generator = MockMvcDocumentGeneratorBuilder(identifier = "test-doc")

        context("if call request()") {
            generator.request(HttpMethod.GET, "/api/example/{id}") {
                pathVariable("id", "id", "id")
            }

            it("set method and urlTemplate") {
                generator.method shouldBe HttpMethod.GET
                generator.urlTemplate shouldBe "/api/example/{id}"
            }

            it("set RequestType to HTTP") {
                generator.requestType shouldBe RequestType.HTTP
            }

            it("add PathVariablesSpec") {
                generator.pathVariablesSpec shouldNotBe null
            }
        }

        context("if call multipart()") {
            generator.multipart(HttpMethod.POST, "/api/example/{id}") {
                pathVariable("id", "id", "id")
            }

            it("set method and urlTemplate") {
                generator.method shouldBe HttpMethod.POST
                generator.urlTemplate shouldBe "/api/example/{id}"
            }

            it("set RequestType to MULTIPART") {
                generator.requestType shouldBe RequestType.MULTIPART
            }

            it("addPathVariablesSpec") {
                generator.pathVariablesSpec shouldNotBe null
            }
        }
    }

    describe("requestHeader() Test") {
        val generator = MockMvcDocumentGeneratorBuilder(identifier = "test-doc")

        context("if add headers") {
            generator.requestHeader {
                header("Authorization", "Bearer token", "Bearer abcdefghijklmnopqrstuvwxyz")
                header("X-Test-Header", "TestHeader", "TestHeader")
            }

            it("set headersSpec") {
                generator.headersSpec shouldNotBe null
            }
        }
    }

    describe("pathParameter() Test") {
        val generator = MockMvcDocumentGeneratorBuilder(identifier = "test-doc")

        context("if add parameters") {
            generator.pathParameter {
                pathVariable("page", "page", "1")
                pathVariable("size", "size", "10")
            }

            it("set parametersSpec") {
                generator.pathVariablesSpec shouldNotBe null
            }
        }
    }

    describe("queryParameter() Test") {
        val generator = MockMvcDocumentGeneratorBuilder(identifier = "test-doc")

        context("if add parameters") {
            generator.requestParameter {
                queryParameter("page", "page", "1")
                queryParameter("size", "size", "10")
            }

            it("set parametersSpec") {
                generator.queryParametersSpec shouldNotBe null
            }
        }
    }

    describe("requestField() Test") {
        val generator = MockMvcDocumentGeneratorBuilder(identifier = "test-doc")

        context("if add response fields") {
            generator.requestField {
                field("id", "id", "id")
                field("name", "name", "name")
            }

            it("set responseFieldsSpec") {
                generator.requestFieldsSpec shouldNotBe null
            }
        }
    }
})
