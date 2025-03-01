package io.github.lcomment.example.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ExampleController {

    @GetMapping("/example/{id}")
    fun example(
        @PathVariable id: Long,
    ): ExampleResponse {
        return ExampleResponse("Hello, World!")
    }

    data class ExampleResponse(
        val message: String,
    )
}
