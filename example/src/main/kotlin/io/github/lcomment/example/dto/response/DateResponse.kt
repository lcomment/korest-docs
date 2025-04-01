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

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Date

data class DateResponse(
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val dateTime1: LocalDateTime = LocalDateTime.now(),
    val dateTime2: ZonedDateTime = ZonedDateTime.now(),
    val dateTime3: OffsetDateTime = OffsetDateTime.now(),
    val dateTime4: Date = Date(),
    val dateTime5: Calendar = Calendar.getInstance(),
)
