package io.github.lcomment.example.controller

import io.github.lcomment.example.dto.response.ApiResponse
import io.github.lcomment.example.dto.response.ExampleResponse
import io.github.lcomment.example.service.ExampleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
}
