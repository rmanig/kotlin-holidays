package de.rmanig.holidays.functions

import java.time.LocalDate
import java.time.Year

class GregorianEaster {

    companion object {

        fun calculateEasterFor(year: Year): LocalDate {
            val y = year.value
            val a = y % 19
            val b = y / 100
            val c = y % 100
            val d = b / 4
            val e = b % 4
            val f = (b + 8) / 25
            val g = (b - f + 1) / 3
            val h = (19 * a + b - d - g + 15) % 30
            val i = c / 4
            val k = c % 4
            val l = (32 + 2 * e + 2 * i - h - k) % 7
            val m = (a + 11 * h + 22 * l) / 451

            val month = (h + l - 7 * m + 114) / 31
            val day = ((h + l - 7 * m + 114) % 31) + 1

            return LocalDate.of(year.value, month, day)
        }

        fun calculateOrthodoxEasterFor(year: Year): LocalDate {
            val julianDate = JulianEaster().calculateOrthodoxEasterFor(year)

            val offset: Int
            if (year.value <= 1582) {
                // up until 1582, julian and gregorian easter dates were identical
                offset = 0
            } else if (year.value >= 1583 && year.value <= 1699) {
                // between the years 1583 and 1699 10 days are added to the julian day count
                offset = 10
            } else {
                // after 1700, 1 day is added for each century, except if the century year is exactly divisible by 400 ( in which case no days are added).
                // Safe until 4100 AD, when one leap day will be removed.
                val a = (year.value - 1700) / 100
                val b = year.value - (year.value % 100)
                val c = if (b % 400 == 0) 0 else 1
                val d = (year.value - (year.value % 100) - 1700) / 400
                offset = a + c - d + 10
            }

            return julianDate.plusDays(offset.toLong())
        }
    }

}
