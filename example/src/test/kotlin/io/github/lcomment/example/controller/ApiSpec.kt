package io.github.lcomment.example.controller

import io.github.lcomment.example.dto.request.ExampleRequest
import io.github.lcomment.example.dto.response.ExampleResponse
import io.github.lcomment.example.enums.ExampleEnum
import io.github.lcomment.example.enums.ReturnCode
import io.github.lcomment.example.service.ExampleService
import io.github.lcomment.korestdocs.mockmvc.KorestDocumentationExtension
import io.github.lcomment.korestdocs.mockmvc.documentation
import io.github.lcomment.korestdocs.mockmvc.extensions.andDocument
import io.github.lcomment.korestdocs.mockmvc.extensions.requestWithDocs
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.doReturn
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.filter.CharacterEncodingFilter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

@SpringBootTest
@ExtendWith(KorestDocumentationExtension::class)
class ApiSpec {

    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var exampleService: ExampleService

    @BeforeEach
    fun setup(
        provider: RestDocumentationContextProvider,
        context: WebApplicationContext,
    ) {
        this.mockMvc = buildRestdocsMockMvc(context, provider)
    }

    @Test
    fun `extension function - GET`() {
        val data = ExampleResponse()
        doReturn(data).`when`(exampleService).get()

        mockMvc.requestWithDocs(HttpMethod.GET, "/example/{id}", 1) {
            header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
            param("param1", "value1")
        }
            .andExpect {
                status { isOk() }
            }
            .andDocument("junit-get-extension-function") {
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

    @Test
    fun `extension function - POST`() {
        val request = ExampleRequest("example")

        mockMvc.requestWithDocs(HttpMethod.POST, "/example/{id}", 1) {
            header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
            content = toJson(request)
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andDocument("junit-post-extension-function") {
                requestHeader {
                    header("Authorization", "Access Token", "Bearer access-token")
                }

                requestField {
                    field("example", "example request", "example")
                }
            }
    }

    @Test
    fun `function - GET`() {
        val data = ExampleResponse()
        doReturn(data).`when`(exampleService).get()

        documentation("junit-get-function") {
            request(HttpMethod.GET, "/example/{id}") {
                pathVariable("id", "아이디", 1)
                queryParameter("param1", "파라미터 1", "value1")
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

    @Test
    fun `function - POST`() {
        documentation("junit-post-function") {
            request(HttpMethod.POST, "/example/{id}") {
                pathVariable("id", "아이디", 1)
            }

            requestHeader {
                header("Authorization", "Access Token", "Bearer access-token")
            }

            requestField {
                field("example", "example request", "example")
            }

            responseField {
                field("code", "Response Code", ReturnCode.SUCCESS.code)
                field("message", "Response Message", ReturnCode.SUCCESS.message)
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

fun toJson(value: Any): String {
    return mapper.writeValueAsString(value)
}

private val mapper: ObjectMapper = ObjectMapper()
    .registerModule(JavaTimeModule())
    .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
