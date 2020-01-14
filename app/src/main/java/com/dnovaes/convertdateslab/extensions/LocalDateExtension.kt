package com.dnovaes.convertdateslab.extensions

import org.threeten.bp.DateTimeUtils
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar


fun LocalDate.toDate() : Date {
    val day = this.dayOfMonth
    val month = this.monthValue
    val year = this.year

    val calendar = GregorianCalendar()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month-1)
    calendar.set(Calendar.DATE, day)
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)
    return calendar.time
}

fun LocalDateTime.toDate(zoneId: ZoneId = ZoneId.of("UTC")) : Date {
    val instant = this.atZone(zoneId).toInstant()
    return DateTimeUtils.toDate(instant)
}
