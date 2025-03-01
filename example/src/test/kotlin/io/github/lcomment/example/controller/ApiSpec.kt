package io.github.lcomment.example.controller

import io.github.lcomment.korestdocs.STRING
import io.github.lcomment.korestdocs.mockmvc.andDocument
import io.github.lcomment.korestdocs.mockmvc.requestWithDocs
import io.github.lcomment.korestdocs.type
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter

@WebMvcTest(ExampleController::class)
@ExtendWith(RestDocumentationExtension::class)
class ApiSpec(
    @Autowired
    private val context: WebApplicationContext,
) {

    @Autowired
    lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(provider: RestDocumentationContextProvider) {
        this.mockMvc = buildRestdocsMockMvc(context, provider)
    }

    @Test
    fun `test www`() {
        mockMvc.requestWithDocs(HttpMethod.GET, "/example/{id}", 1, dsl = {
            header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
        })
            .andExpect { status().isOk }
            .andDo { print() }
            .andDocument("example") {
                requestHeader {
                    add("Authorization", "액세스 토큰")
                }

                pathParameter {
                    add("id", "아이디")
                }

                responseField {
                    add("message" type STRING means "메시지")
                }
            }
    }
}

fun buildRestdocsMockMvc(
    context: WebApplicationContext,
    provider: RestDocumentationContextProvider,
): MockMvc {
    return MockMvcBuilders
        .webAppContextSetup(context)
        .apply<DefaultMockMvcBuilder>(documentationConfiguration(provider))
        .addFilter<DefaultMockMvcBuilder>(CharacterEncodingFilter("UTF-8", true))
        .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
        .build()
}
