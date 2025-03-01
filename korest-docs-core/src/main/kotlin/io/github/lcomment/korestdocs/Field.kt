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

package io.github.lcomment.korestdocs

import org.springframework.restdocs.payload.FieldDescriptor

class Field(
    val descriptor: FieldDescriptor,
) {

    private var default: String
        get() = descriptor.attributes["default"] as String
        set(value) {
            descriptor.attributes["default"] = value
        }

    private var example: String
        get() = descriptor.attributes["example"] as String
        set(value) {
            descriptor.attributes["example"] = value
        }

    infix fun means(value: String): Field {
        descriptor.description(value)
        return this
    }

    infix fun attributes(block: Field.() -> Unit): Field {
        block()
        return this
    }

    infix fun withDefaultValue(value: String): Field {
        this.default = value
        return this
    }

    infix fun example(value: String): Field {
        this.example = value
        return this
    }

    infix fun isOptional(value: Boolean): Field {
        if (value) descriptor.optional()
        return this
    }

    infix fun isIgnored(value: Boolean): Field {
        if (value) descriptor.ignored()
        return this
    }
}
