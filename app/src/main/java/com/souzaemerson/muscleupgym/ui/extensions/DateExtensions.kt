package com.souzaemerson.muscleupgym.ui.extensions

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

private val locale = Locale("pt", "BR")
private val textStyle = TextStyle.FULL

fun LocalDate.getDayOfWeekInPortuguese(): String {
    return this.dayOfWeek.getDisplayName(textStyle, locale)
}

fun LocalDate.getMonthInPortuguese(): String {
    return this.month.getDisplayName(textStyle, locale).uppercase()
}