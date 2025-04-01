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

package io.github.lcomment.example.dto.response

import io.github.lcomment.example.enums.ReturnCode
import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
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
