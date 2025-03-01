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

package io.github.lcomment.korestdocs.spec

import org.springframework.restdocs.hypermedia.HypermediaDocumentation
import org.springframework.restdocs.hypermedia.LinkDescriptor
import org.springframework.restdocs.hypermedia.LinkExtractor
import org.springframework.restdocs.hypermedia.LinksSnippet

internal class LinksSpecBuilder(
    private val links: MutableList<LinkDescriptor> = mutableListOf<LinkDescriptor>(),
) : LinksSpec {

    override fun add(
        rel: String,
        description: String?,
        optional: Boolean,
        ignored: Boolean,
        attributes: Map<String, Any?>,
    ) = add(
        HypermediaDocumentation.linkWithRel(rel)
            .description(description)
            .apply {
                if (optional) optional()
                if (ignored) ignored()
            }
            .attributes(*attributes.toAttributes()),
    )

    override fun add(linkDescriptor: LinkDescriptor) {
        links.add(linkDescriptor)
    }

    fun build(
        relaxed: Boolean,
        linkExtractor: LinkExtractor?,
        attributes: Map<String, Any?>,
    ): LinksSnippet =
        if (relaxed) {
            linkExtractor?.let {
                HypermediaDocumentation.relaxedLinks(
                    linkExtractor,
                    attributes,
                    links,
                )
            } ?: HypermediaDocumentation.relaxedLinks(attributes, links)
        } else {
            linkExtractor?.let {
                HypermediaDocumentation.links(
                    linkExtractor,
                    attributes,
                    links,
                )
            } ?: HypermediaDocumentation.links(attributes, links)
        }
}
