package com.dnovaes.convertdateslab

import org.threeten.bp.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

fun Date.toLocalDate(): LocalDate {
    val calendar = GregorianCalendar()
    calendar.time = this

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)+1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    return LocalDate.of(year, month, day)
}

