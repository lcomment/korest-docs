package io.github.lcomment.example.enums

enum class ReturnCode(
    val code: String,
    val message: String,
) {
    SUCCESS("0000", "Success"),
    CODE_1("0001", "Code 1"),
    CODE_2("0002", "Code 2"),
    ERROR("9999", "Error"),
}
