package io.github.lcomment.example.dto.response

import io.github.lcomment.example.enums.ExampleEnum

data class ExampleResponse(
    val booleanData: Boolean = true,
    val stringData: String = "Hello, World!",
    val numberData: NumberResponse = NumberResponse(),
    val arrayData: ArrayResponse = ArrayResponse(),
    val enumData: ExampleEnum = ExampleEnum.EXAMPLE1,
    val DateData: DateResponse = DateResponse(),
)
