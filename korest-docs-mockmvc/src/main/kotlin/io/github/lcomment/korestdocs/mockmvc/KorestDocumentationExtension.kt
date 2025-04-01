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

package io.github.lcomment.korestdocs.mockmvc

import io.github.lcomment.korestdocs.mockmvc.context.DefaultMockMvcContext
import io.github.lcomment.korestdocs.mockmvc.context.MockMvcContextHolder
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.api.extension.ParameterResolver
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.context.WebApplicationContext

class KorestDocumentationExtension(
    private val outputDirectory: String? = null,
) : BeforeEachCallback, AfterEachCallback, ParameterResolver {

    override fun beforeEach(context: ExtensionContext) {
        val delegate = getDelegate(context)
        delegate.beforeTest(context.requiredTestClass, context.requiredTestMethod.name)

        val applicationContext = SpringExtension.getApplicationContext(context) as WebApplicationContext
        MockMvcContextHolder.setContext(DefaultMockMvcContext(applicationContext, delegate))
    }

    override fun afterEach(context: ExtensionContext) {
        this.getDelegate(context).afterTest()
        MockMvcContextHolder.clearContext()
    }

    override fun supportsParameter(parameterContext: ParameterContext, extensionContext: ExtensionContext): Boolean {
        if (isTestMethodContext(extensionContext)) {
            return RestDocumentationContextProvider::class.java.isAssignableFrom(parameterContext.parameter.getType())
        }

        return false
    }

    override fun resolveParameter(parameterContext: ParameterContext, context: ExtensionContext): Any {
        return RestDocumentationContextProvider {
            getDelegate(context).beforeOperation()
        }
    }

    private fun isTestMethodContext(context: ExtensionContext): Boolean {
        return context.testClass.isPresent && context.testMethod.isPresent
    }

    private fun getDelegate(context: ExtensionContext): ManualRestDocumentation {
        val namespace = ExtensionContext.Namespace.create(javaClass, context.uniqueId)
        return context.getStore(namespace)
            .getOrComputeIfAbsent(
                ManualRestDocumentation::class.java,
                this::createManualRestDocumentation,
                ManualRestDocumentation::class.java,
            )
    }

    private fun createManualRestDocumentation(key: Class<ManualRestDocumentation>): ManualRestDocumentation {
        return if (this.outputDirectory != null) {
            ManualRestDocumentation(this.outputDirectory)
        } else {
            ManualRestDocumentation()
        }
    }
}
