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

import io.github.lcomment.korestdocs.spec.DocumentSpec
import io.github.lcomment.korestdocs.spec.documentationScope
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.ResultHandler

fun ResultActions.andDocument(
    identifier: String,
    configure: DocumentSpec.() -> Unit,
): ResultActions {
    val documentSpec = documentationScope(identifier).apply(configure)

    return andDo { documentSpec.toResultHandler() }
}

fun ResultActionsDsl.andDocument(
    identifier: String,
    configure: DocumentSpec.() -> Unit,
): ResultActionsDsl {
    val documentSpec = documentationScope(identifier).apply(configure)

    return andDo { handle(documentSpec.toResultHandler()) }
}

private fun DocumentSpec.toResultHandler(): ResultHandler {
    return MockMvcRestDocumentation.document(
        identifier,
        requestPreprocessor,
        responsePreprocessor,
        *snippets.toTypedArray(),
    )
}
