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

import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.core.spec.style.DescribeSpec

internal class LinksSpecTest : DescribeSpec({

    describe("add() Method Test") {
        val linksSpec = LinksSpecBuilder()

        context("if add link") {

            it("should successfully add link") {
                shouldNotThrow<Exception> {
                    linksSpec.add(
                        rel = "test",
                        description = "test",
                    )
                }
            }
        }
    }
})
