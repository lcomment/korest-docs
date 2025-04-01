package io.github.lcomment.korestdocs.mockmvc.extensions

import io.github.lcomment.korestdocs.mockmvc.MockMvcDocumentGenerator
import io.github.lcomment.korestdocs.mockmvc.MockMvcDocumentGeneratorBuilder

fun documentationScope(identifier: String): MockMvcDocumentGenerator {
    return MockMvcDocumentGeneratorBuilder(identifier)
}
