package io.github.lcomment.example.controller

import io.github.lcomment.example.dto.response.ExampleResponse
import io.github.lcomment.example.enums.ExampleEnum
import io.github.lcomment.example.enums.ReturnCode
import io.github.lcomment.example.service.ExampleService
import io.github.lcomment.korestdocs.mockmvc.andDocument
import io.github.lcomment.korestdocs.mockmvc.requestWithDocs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.doReturn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
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

    @MockBean
    lateinit var exampleService: ExampleService

    @BeforeEach
    fun setup(provider: RestDocumentationContextProvider) {
        this.mockMvc = buildRestdocsMockMvc(context, provider)
    }

    @Test
    fun `test www`() {
        val data = ExampleResponse()
        doReturn(data).`when`(exampleService).get()

        mockMvc.requestWithDocs(HttpMethod.GET, "/example/{id}", 1, dsl = {
            header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
            param("param1", "value1")
        })
            .andExpect { status().isOk }
            .andDo { print() }
            .andDocument("example") {
                requestHeader {
                    header("Authorization", "Access Token", "Bearer access-token")
                }

                pathParameter {
                    pathVariable("id", "id", 1)
                }

                requestParameter {
                    queryParameter("param1", "parameter 1", "value1")
                }

                responseField {
                    field("code", "Response Code", ReturnCode.SUCCESS.code)
                    field("message", "Response Message", ReturnCode.SUCCESS.message)
                    field("data", "Response Data", data)
                    field("data.booleanData", "Boolean Data", data.booleanData)
                    optionalField("data.stringData", "String Data", data.stringData)
                    field("data.numberData", "Number Data", data.numberData)
                    optionalField("data.numberData.number1", "Integer Data", data.numberData.number1)
                    field("data.numberData.number2", "Long Data", data.numberData.number2)
                    field("data.numberData.number3", "Short Data", data.numberData.number3)
                    field("data.numberData.number4", "Float Data", data.numberData.number4)
                    field("data.numberData.number5", "Double Data", data.numberData.number5)
                    field("data.arrayData", "Array Data", data.arrayData)
                    optionalField("data.arrayData.array1", "Array Data", data.arrayData.array1)
                    field("data.arrayData.array2", "List Data", data.arrayData.array2)
                    field("data.arrayData.array3", "Set Data", data.arrayData.array3)
                    field("data.enumData", "Enum Data", ExampleEnum.EXAMPLE1)
                    field("data.dateData", "Date Data", data.DateData)
                    field("data.dateData.date", "Date Data", data.DateData.date)
                    field("data.dateData.time", "Time Data", data.DateData.time)
                    optionalField("data.dateData.dateTime1", "DateTime Data", data.DateData.dateTime1)
                    field("data.dateData.dateTime2", "DateTime Data", data.DateData.dateTime2)
                    field("data.dateData.dateTime3", "DateTime Data", data.DateData.dateTime3)
                    field("data.dateData.dateTime4", "DateTime Data", data.DateData.dateTime4)
                    field("data.dateData.dateTime5", "DateTime Data", data.DateData.dateTime5)
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
