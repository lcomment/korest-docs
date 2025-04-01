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

package io.github.lcomment.korestdocs.mockmvc.extensions

fun extractPathParameters(
    urlTemplate: String,
    pathVariables: Map<String, Any>?,
): List<Any> {
    if (pathVariables.isNullOrEmpty()) {
        return emptyList()
    }

    val regex = "\\{(.*?)}".toRegex()

    return regex.findAll(urlTemplate)
        .mapNotNull { pathVariables[it.groupValues[1]] }
        .toList()
}
