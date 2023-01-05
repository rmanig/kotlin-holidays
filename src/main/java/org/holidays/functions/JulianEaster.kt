package org.holidays.functions

import java.time.LocalDate
import java.time.Year

// Copied from https://github.com/Loyolny/when_easter
// Graciously allowed by Michał Nierebiński (https://github.com/Loyolny)
class JulianEaster {

    fun calculateEasterFor(year: Year) : LocalDate {
        val g = year.value % 19 + 1
        val s = (year.value - 1600) / 100 - (year.value - 1600) / 400
        val l = Math.floorDiv((Math.floorDiv(year.value - 1400, 100) * 8), 25)

        val p2 = Math.floorMod((3 - 11 * g + s - l), 30)

        val p: Int
        if (p2 == 29 || (p2 == 28 && g > 11)) {
            p = p2 - 1
        } else {
            p = p2
        }

        val d = (year.value + year.value / 4 - year.value / 100 + year.value / 400) % 7
        val d2 = (8 - d) % 7

        val p3 = (80 + p) % 7
        val x2 = d2 - p3

        val x = Math.floorMod((x2 - 1), 7) + 1
        val e = p+x

        val date: LocalDate
        if (e < 11) {
            date = LocalDate.of(year.value, 3, e + 21)
        } else {
            date = LocalDate.of(year.value,4,e - 10)
        }
        return date
    }

    fun calculateOrthodoxEasterFor(year: Year) : LocalDate {
        val y = year.value
        val g = y % 19
        val i = (19 * g + 15) % 30
        val j = (year.value + year.value / 4 + i) % 7
        val month = 3 + (i - j + 40) / 44
        val day = i - j + 28 - 31 * (month / 4)
        return LocalDate.of(year.value, month, day)
    }

}
