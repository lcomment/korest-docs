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

package io.github.lcomment.example.controller

import io.github.lcomment.example.dto.request.ExampleMultipartRequest
import io.github.lcomment.example.dto.request.ExampleRequest
import io.github.lcomment.example.dto.response.ExampleResponse
import io.github.lcomment.example.enums.ExampleEnum
import io.github.lcomment.example.enums.ReturnCode
import io.github.lcomment.example.service.ExampleService
import io.github.lcomment.korestdocs.mockmvc.KorestDocumentationExtension
import io.github.lcomment.korestdocs.mockmvc.documentation
import io.github.lcomment.korestdocs.mockmvc.extensions.andDocument
import io.github.lcomment.korestdocs.mockmvc.extensions.multipartWithDocs
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
import org.springframework.mock.web.MockMultipartFile
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.multipart
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
internal class ApiSpec {

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
                    field("arrayData.array1", "Array Data 1", request.arrayData.array1)
                    field("arrayData.array2", "Array Data 2", request.arrayData.array2)
                    field("arrayData.array3", "Array Data 3", request.arrayData.array3)
                    field("enumData", "Enum Data", ExampleEnum.EXAMPLE1)
                    field("objectData.value1", "Object Value 1", request.objectData.value1)
                    field("objectData.value2", "Object Value 2", request.objectData.value2)
                    field("objectData.value3", "Object Value 3", request.objectData.value3)
                }
            }
    }

    @Test
    fun `extension function - MULTIPART POST`() {
        val image1 = MockMultipartFile("images", "", "image/png", "image1".toByteArray())
        val image2 = MockMultipartFile("images", "", "image/png", "image2".toByteArray())
        val request = ExampleMultipartRequest(listOf(image1, image2))

        mockMvc.multipartWithDocs(HttpMethod.POST, "/example") {
            header(HttpHeaders.AUTHORIZATION, "Bearer access-token")
            file(image1)
            file(image2)
            param("message", "exampleMessage")
            contentType = MediaType.MULTIPART_FORM_DATA
            accept = MediaType.APPLICATION_JSON
            with {
                it.method = HttpMethod.POST.name()
                it
            }
        }
            .andExpect { status { isOk() } }
            .andDocument("junit-multipart-post-extension-function") {
                requestHeader {
                    header("Authorization", "Access Token", "Bearer access-token")
                }

                requestPart {
                    part("images", "Images", image1)
                    part("images", "Images", image2)
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
            }

            requestParameter {
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
                field("example", "Example Request", "example")
                ignoredField("arrayData")
                field("arrayData.array1", "Array Data 1", listOf("item1", "item2"))
                field("arrayData.array2", "Array Data 2", setOf("item1", "item2"))
                field("arrayData.array3", "Array Data 3", arrayOf("item1", "item2"))
                field("enumData", "Enum Data", ExampleEnum.EXAMPLE1)
                field("objectData.value1", "Object Value 1", "value1")
                field("objectData.value2", "Object Value 2", 1)
                field("objectData.value3", "Object Value 3", true)
            }

            responseField {
                field("code", "Response Code", ReturnCode.SUCCESS.code)
                field("message", "Response Message", ReturnCode.SUCCESS.message)
            }
        }
    }

    @Test
    fun `function - MULTIPART POST`() {
        val image1 = MockMultipartFile("images", "", "image/png", "image1".toByteArray())
        val image2 = MockMultipartFile("images", "", "image/png", "image2".toByteArray())
        val request = ExampleMultipartRequest(listOf(image1, image2))

        documentation("junit-multipart-post-function") {
            multipart(HttpMethod.POST, "/example")

            requestPart {
                part("images", "Images", image1)
                part("images", "Images", image2)
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
