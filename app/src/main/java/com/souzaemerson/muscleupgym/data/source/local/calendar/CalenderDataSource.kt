package com.souzaemerson.muscleupgym.data.source.local.calendar

import com.souzaemerson.muscleupgym.ui.extensions.getDayOfWeekInPortuguese
import com.souzaemerson.muscleupgym.ui.extensions.getMonthInPortuguese
import java.time.LocalDate

class CalendarDataSource {
    companion object {

        private val today: LocalDate = LocalDate.now()

        fun setRangeOfDates(
            dates: MutableList<LocalDate>,
            daysBeforeToday: Int = -7,
            daysAfterToday: Int = 7
        ): List<LocalDate> {
            for (i in daysBeforeToday..daysAfterToday) {
                dates.add(today.plusDays(i.toLong()))
            }
            return dates
        }


        fun getHeaderPhrase(): String {
            return "${getDayOfWeek()}, \n${getDayOfMonth()} de ${
                getMonth().lowercase().replaceFirstChar { it.uppercase() }
            } de ${getYear()}"
        }

        private fun getDayOfWeek(): String {
            return today.getDayOfWeekInPortuguese().replaceFirstChar { it.uppercase() }
        }

        private fun getMonth(): String {
            return today.getMonthInPortuguese()
        }

        private fun getYear(): String {
            return today.year.toString()
        }

        private fun getDayOfMonth() = today.dayOfMonth.toString()

    }
}
