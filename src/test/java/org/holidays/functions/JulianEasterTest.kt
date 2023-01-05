package org.holidays.functions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Year

internal class JulianEasterTest {

    @Test
    fun testCalculateEasterFor() {
        assertEquals(LocalDate.parse("0960-04-20"), JulianEaster().calculateEasterFor(Year.of(960)))
        assertEquals(LocalDate.parse("1800-04-13"), JulianEaster().calculateEasterFor(Year.of(1800)))
        assertEquals(LocalDate.parse("1899-04-02"), JulianEaster().calculateEasterFor(Year.of(1899)))
        assertEquals(LocalDate.parse("1900-04-15"), JulianEaster().calculateEasterFor(Year.of(1900)))
        assertEquals(LocalDate.parse("1999-04-04"), JulianEaster().calculateEasterFor(Year.of(1999)))
        assertEquals(LocalDate.parse("2000-04-23"), JulianEaster().calculateEasterFor(Year.of(2000)))
        assertEquals(LocalDate.parse("2025-04-20"), JulianEaster().calculateEasterFor(Year.of(2025)))
        assertEquals(LocalDate.parse("2035-03-25"), JulianEaster().calculateEasterFor(Year.of(2035)))
        assertEquals(LocalDate.parse("2067-04-03"), JulianEaster().calculateEasterFor(Year.of(2067)))
        assertEquals(LocalDate.parse("2099-04-12"), JulianEaster().calculateEasterFor(Year.of(2099)))
    }

    @Test
    fun testCalculateOrthodoxEasterFor() {
        assertEquals(LocalDate.parse("0960-04-22"), JulianEaster().calculateOrthodoxEasterFor(Year.of(960)))
        assertEquals(LocalDate.parse("1500-04-19"), JulianEaster().calculateOrthodoxEasterFor(Year.of(1500)))
        assertEquals(LocalDate.parse("2000-04-17"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2000)))
        assertEquals(LocalDate.parse("2001-04-02"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2001)))
        assertEquals(LocalDate.parse("2015-03-30"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2015)))
        assertEquals(LocalDate.parse("2016-04-18"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2016)))
        assertEquals(LocalDate.parse("2017-04-03"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2017)))
        assertEquals(LocalDate.parse("2020-04-06"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2020)))
        assertEquals(LocalDate.parse("2050-04-04"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2050)))
        assertEquals(LocalDate.parse("2100-04-18"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2100)))
        assertEquals(LocalDate.parse("2500-04-08"), JulianEaster().calculateOrthodoxEasterFor(Year.of(2500)))
    }

}