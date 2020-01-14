package com.kaefer.convertdateslab.extensions

import org.threeten.bp.Clock
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

fun Clock.getCurrentOffset(): ZoneOffset {
    return zone.rules.getOffset(LocalDateTime.now())
}
