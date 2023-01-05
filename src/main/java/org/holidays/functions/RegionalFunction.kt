package org.holidays.functions

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

enum class RegionalFunction {

    de_buss_und_bettag {
        override fun apply(arguments: Map<String, Any>): LocalDate {
            val year: Int = (arguments["year"] ?: throw IllegalArgumentException("year is missing!")) as Int
            return LocalDate.of(year, 11, 23)
                    .with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY))
        }
    };

    abstract fun apply(arguments: Map<String, Any>) : LocalDate

}