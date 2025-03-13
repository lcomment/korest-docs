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
