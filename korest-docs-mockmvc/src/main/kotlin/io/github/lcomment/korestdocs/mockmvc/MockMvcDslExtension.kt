package io.github.lcomment.korestdocs.mockmvc

import io.github.lcomment.korestdocs.spec.DocumentSpec
import io.github.lcomment.korestdocs.spec.documentationScope
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.ResultActionsDsl
import org.springframework.test.web.servlet.ResultHandler

fun ResultActionsDsl.andDocument(identifier: String, configure: DocumentSpec.() -> Unit): ResultActionsDsl =
    andDo { handle(documentationScope(identifier).apply(configure).toResultHandler()) }

private fun DocumentSpec.toResultHandler(): ResultHandler {
    return MockMvcRestDocumentation.document(
        identifier,
        requestPreprocessor,
        responsePreprocessor,
        *snippets.toTypedArray(),
    )
}
