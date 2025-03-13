package io.github.lcomment.example.dto.response

data class ArrayResponse(
    val array1: Array<String> = arrayOf("a", "b"),
    val array2: List<String> = listOf("c", "d"),
    val array3: Set<String> = setOf("e", "f"),
)
