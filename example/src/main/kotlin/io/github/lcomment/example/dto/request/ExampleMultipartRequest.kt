package io.github.lcomment.example.dto.request

import jakarta.validation.constraints.NotBlank
import org.springframework.web.multipart.MultipartFile

data class ExampleMultipartRequest(
    val images: List<MultipartFile> = emptyList(),
    @field:NotBlank
    var message: String? = null,
)
