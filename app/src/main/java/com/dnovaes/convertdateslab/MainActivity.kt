package com.dnovaes.convertdateslab

import android.graphics.Typeface.BOLD
import android.widget.ArrayAdapter
import androidx.appcompat.widget.LinearLayoutCompat.VERTICAL
import com.dnovaes.convertdateslab.activities.RenderableActivity
import com.dnovaes.convertdateslab.constants.Dimensions.PADDING_DEFAULT
import com.dnovaes.convertdateslab.constants.Dimensions.PADDING_TEXTVIEW_DEFAULT
import com.dnovaes.convertdateslab.extensions.getCurrentOffset
import com.dnovaes.convertdateslab.extensions.toDate
import com.dnovaes.convertdateslab.helpers.DateHelper.AMERICAN_DATE
import com.dnovaes.convertdateslab.helpers.DateHelper.getDate
import com.dnovaes.printcharizardlib.asciiArtViewComponent
import org.threeten.bp.Clock
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.TemporalAdjusters.nextOrSame
import org.threeten.bp.temporal.TemporalAdjusters.previousOrSame
import trikita.anvil.BaseDSL.CENTER
import trikita.anvil.BaseDSL.MATCH
import trikita.anvil.BaseDSL.WRAP
import trikita.anvil.BaseDSL.dip
import trikita.anvil.BaseDSL.onItemSelected
import trikita.anvil.BaseDSL.padding
import trikita.anvil.BaseDSL.size
import trikita.anvil.BaseDSL.text
import trikita.anvil.DSL.adapter
import trikita.anvil.DSL.gravity
import trikita.anvil.DSL.id
import trikita.anvil.DSL.linearLayout
import trikita.anvil.DSL.orientation
import trikita.anvil.DSL.scrollView
import trikita.anvil.DSL.selection
import trikita.anvil.DSL.spinner
import trikita.anvil.DSL.textView
import trikita.anvil.DSL.typeface
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MainActivity : RenderableActivity() {

    companion object {
        const val LABEL_DATE_UTIL = 0
        const val LABEL_LOCALDATE = 1
        const val LABEL_LOCALDATETIME = 2
    }

    private lateinit var timeZonesAdapter: ArrayAdapter<Any>
    private lateinit var timeZones: MutableMap<String, String>
    private var selectedTimeZone: Int? = null

    override fun content() {
        if(!::timeZones.isInitialized) {
            timeZones = getAllZoneIdsAndOffset().toSortedMap(compareBy { it })
            timeZonesAdapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, timeZones.toList())
            val clock = Clock.systemDefaultZone()
            selectedTimeZone = timeZones.toList().indexOf(
                Pair(clock.zone.toString(), clock.getCurrentOffset().toString()))
        }
        scrollView {
            size(MATCH, MATCH)
            linearLayout {
                size(MATCH, WRAP)
                orientation(VERTICAL)

                spinner {
                    size(MATCH, WRAP)
                    adapter(timeZonesAdapter)
                    padding(dip(PADDING_DEFAULT))
                    selectedTimeZone?.let { selection(it) }
                    onItemSelected { _, _, indexSelected, _ ->
                        //Timezone values like GMT-03/GMT-04 from date.toString are deprecated and incorrect
                        val zoneString = timeZones.entries.toTypedArray()[indexSelected].key
                        TimeZone.setDefault(TimeZone.getTimeZone(zoneString))
                        selectedTimeZone = indexSelected
                    }
                }

                //Date
                textView {
                    id(LABEL_DATE_UTIL)
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("DATE() Now:")
                    typeface(null, BOLD)
                    gravity(CENTER)
                }

                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text(Date().toString())
                    gravity(CENTER)
                }

                //LocalDate
                textView {
                    id(LABEL_LOCALDATETIME)
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("LocalDateTime().now")
                    typeface(null, BOLD)
                    gravity(CENTER)
                }

                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text(LocalDateTime.now().toString())
                    gravity(CENTER)
                }

                //LocalDate
                textView {
                    id(LABEL_LOCALDATE)
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("LocalDate().now Now")
                    typeface(null, BOLD)
                    gravity(CENTER)
                }

                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text(LocalDate.now().toString())
                    gravity(CENTER)
                }

                //ZoneId
                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("ZoneId")
                    typeface(null, BOLD)
                    gravity(CENTER)
                }
                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    selectedTimeZone?.let {
                        val zoneId = ZoneId.of(timeZones.entries.toTypedArray()[it].key)
                        val zoneOffset = zoneId.rules.getOffset(LocalDateTime.now(zoneId))
                        text("$zoneId $zoneOffset")
                    }
                    gravity(CENTER)
                }

                //daysOfWeek
                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("getDaysOfWeek()")
                    typeface(null, BOLD)
                    gravity(CENTER)
                }
                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("by Date: "+getDaysOfWeek().toString())
                    gravity(CENTER)
                }
                textView {
                    size(MATCH, WRAP)
                    padding(dip(PADDING_TEXTVIEW_DEFAULT ))
                    text("byLocalDate: "+getDaysOfWeekNew().toString())
                    gravity(CENTER)
                }
                asciiArtViewComponent {}
            }
        }

    }

    private fun getAllZoneIdsAndOffset(): MutableMap<String, String> {
        val localDateTime = LocalDateTime.now()
        val formattedZoneList: MutableMap<String, String> = mutableMapOf()

        for(zoneId in ZoneId.getAvailableZoneIds()) {
            val id = ZoneId.of(zoneId)

            val zonedDateTime = localDateTime.atZone(id)
            val zoneOffset = zonedDateTime.offset
            val offset = zoneOffset.id.replace("Z", "+00:00")
            formattedZoneList[id.toString()] = offset
        }
        return formattedZoneList
    }

    fun getDaysOfWeek(): Pair<Date, Date>? {
        val today = LocalDate.now()
        val startDate = getDate(formatDate(today.with(previousOrSame(DayOfWeek.SUNDAY)), AMERICAN_DATE), AMERICAN_DATE) ?: return null
        val endDate = getDate(formatDate(today.with(nextOrSame(DayOfWeek.SATURDAY)), AMERICAN_DATE), AMERICAN_DATE) ?: return null
        return Pair(startDate, endDate)
    }

    fun getDaysOfWeekNew(): Pair<String, String> {
        val BRAZILIAN_DATE = "dd-MM-yyyy"

        val today = LocalDate.now()

        val localStartDate = today.with(previousOrSame(DayOfWeek.SUNDAY))
        val startDate = SimpleDateFormat(BRAZILIAN_DATE, Locale.getDefault()).format(
            localStartDate.toDate())

        val localEndDate = today.with(nextOrSame(DayOfWeek.SATURDAY))
        val endDate = SimpleDateFormat(BRAZILIAN_DATE, Locale.getDefault()).format(
            localEndDate.toDate())
        return Pair(startDate, endDate)
    }

    fun formatDate(date: LocalDate?, format: String): String {
        val localDate = date ?: return ""
        val formatter = DateTimeFormatter.ofPattern(format)
        return localDate.format(formatter)
    }
}
