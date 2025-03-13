package io.github.lcomment.example.dto.response

import io.github.lcomment.example.enums.ReturnCode

data class ApiResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null,
) {

    constructor(returnCode: ReturnCode, data: T? = null) : this(returnCode.code, returnCode.message, data)

    companion object {

        fun <T> of(returnCode: ReturnCode, data: T? = null) = ApiResponse(returnCode, data)

        fun <T> success(data: T? = null) = ApiResponse(ReturnCode.SUCCESS, data)

        fun error(errorMessage: String?) = ApiResponse(ReturnCode.ERROR, errorMessage)
    }
}
