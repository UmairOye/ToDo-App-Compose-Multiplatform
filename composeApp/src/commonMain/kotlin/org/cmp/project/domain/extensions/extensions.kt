package org.cmp.project.domain.extensions

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun Long?.formatLongToDate(): String {
    if (this == null) return "No Reminder"
    val instant = Instant.fromEpochMilliseconds(this)
    val localDate = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    return "${localDate.dayOfMonth} ${localDate.month.name.lowercase().replaceFirstChar { it.uppercase() }}, ${localDate.year}"
}