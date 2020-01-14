package com.dnovaes.convertdateslab.helpers

import android.util.Log
import com.dnovaes.convertdateslab.MainActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateHelper {

    val UTC = "yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'"
    val UTC_WITHOUT_MILLIS = "yyyy-MM-dd\'T\'HH:mm:ss\'Z\'"
    val UTC_WITHOUT_MILLIS_WITHOUT_ZONE = "yyyy-MM-dd\'T\'HH:mm:ss"
    val SHORT_DATE = "dd/MM/yy"
    val AMERICAN_DATE = "yyyy-MM-dd"
    val DATE_TIME = "dd/MM/yyyy HH:mm"
    val TIMESTAMP = "yyyyMMddHHmmss"

    fun isThisDateValid(dateToValidate: String?, dateFormat: String): Boolean {
        if (dateToValidate == null) {
            return false
        }
        val sdf = SimpleDateFormat(dateFormat, Locale.getDefault())
        sdf.isLenient = false
        try {
            //if not valid, it will throw ParseException
            sdf.parse(dateToValidate)
        } catch (e: ParseException) {
            return false
        }
        return true
    }

    fun getDate(strDate: String?, format: String?): Date? {
        var date: Date? = null
        if (strDate != null && !strDate.isEmpty() &&
            isThisDateValid(strDate, getFormatOfDate(strDate))) {
            try {
                date = SimpleDateFormat(format,
                    Locale.getDefault()).parse(strDate)
            } catch (e: ParseException) {
                Log.e(MainActivity::class.java.name, e.message)
            }

        }
        return date
    }

    fun getFormatOfDate(strDate: String?): String {
        return when {
            strDate == null -> ""
            isThisDateValid(strDate, SHORT_DATE) && strDate.length == SHORT_DATE.length -> SHORT_DATE
            isThisDateValid(strDate, AMERICAN_DATE) && !strDate.contains("T") && !strDate.contains("Z") -> AMERICAN_DATE
            isThisDateValid(strDate, DATE_TIME) -> DATE_TIME
            isThisDateValid(strDate, UTC) -> UTC
            isThisDateValid(strDate, UTC_WITHOUT_MILLIS) -> UTC_WITHOUT_MILLIS
            isThisDateValid(strDate, UTC_WITHOUT_MILLIS_WITHOUT_ZONE) -> UTC_WITHOUT_MILLIS_WITHOUT_ZONE
            else -> ""
        }
    }
}
