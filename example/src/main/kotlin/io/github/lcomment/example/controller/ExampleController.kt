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
import io.github.lcomment.example.dto.response.ApiResponse
import io.github.lcomment.example.dto.response.ExampleResponse
import io.github.lcomment.example.service.ExampleService
import jakarta.validation.Valid
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController(
    private val exampleService: ExampleService,
) {

    @GetMapping("/example/{id}")
    fun get(
        @PathVariable id: Long,
    ): ApiResponse<ExampleResponse> {
        val data = exampleService.get()

        return ApiResponse.success(data)
    }

    @PostMapping("/example/{id}")
    fun post(
        @PathVariable id: Long,
        @RequestBody @Valid request: ExampleRequest,
    ): ApiResponse<Unit> {
        exampleService.post(request)

        return ApiResponse.success()
    }

    @PostMapping(
        "/example",
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun saveImages(
        @ModelAttribute @Valid request: ExampleMultipartRequest,
//        @RequestParam message: String,
    ): ApiResponse<Unit> {
        return ApiResponse.success()
    }
}
