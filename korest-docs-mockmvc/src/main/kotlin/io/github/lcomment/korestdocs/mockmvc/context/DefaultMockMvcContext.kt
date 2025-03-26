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

package io.github.lcomment.korestdocs.mockmvc.context

import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

internal class DefaultMockMvcContext(
    private val mockMvc: MockMvc,
) : MockMvcContext {

    constructor(
        applicationContext: WebApplicationContext,
        restDocumentationContextProvider: RestDocumentationContextProvider,
    ) : this(
        MockMvcBuilders
            .webAppContextSetup(applicationContext)
            .apply<DefaultMockMvcBuilder>(documentationConfiguration(restDocumentationContextProvider))
            .build(),
    )

    override fun getMockMvc(): MockMvc {
        return mockMvc
    }
}
