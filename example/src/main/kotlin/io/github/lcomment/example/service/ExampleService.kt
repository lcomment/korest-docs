package io.github.lcomment.example.service

import io.github.lcomment.example.dto.response.ExampleResponse
import org.springframework.stereotype.Service

@Service
class ExampleService {

    fun get(): ExampleResponse {
        return ExampleResponse()
    }
}
