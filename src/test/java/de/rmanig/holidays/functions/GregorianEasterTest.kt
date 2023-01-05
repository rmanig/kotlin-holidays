package de.rmanig.holidays.functions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.Year

internal class GregorianEasterTest {

    @Test
    fun testCalculateEasterFor() {
        assertEquals(LocalDate.of(960, 4, 20), GregorianEaster().calculateEasterFor(Year.of(960)))
        assertEquals(LocalDate.parse("1800-04-13"), GregorianEaster().calculateEasterFor(Year.of(1800)))
        assertEquals(LocalDate.parse("1899-04-02"), GregorianEaster().calculateEasterFor(Year.of(1899)))
        assertEquals(LocalDate.parse("1900-04-15"), GregorianEaster().calculateEasterFor(Year.of(1900)))
        assertEquals(LocalDate.parse("1999-04-04"), GregorianEaster().calculateEasterFor(Year.of(1999)))
        assertEquals(LocalDate.parse("2000-04-23"), GregorianEaster().calculateEasterFor(Year.of(2000)))
        assertEquals(LocalDate.parse("2025-04-20"), GregorianEaster().calculateEasterFor(Year.of(2025)))
        assertEquals(LocalDate.parse("2035-03-25"), GregorianEaster().calculateEasterFor(Year.of(2035)))
        assertEquals(LocalDate.parse("2067-04-03"), GregorianEaster().calculateEasterFor(Year.of(2067)))
        assertEquals(LocalDate.parse("2099-04-12"), GregorianEaster().calculateEasterFor(Year.of(2099)))
    }

    @Test
    fun testCalculateOrthodoxEasterFor() {
        assertEquals(LocalDate.parse("2000-04-30"), GregorianEaster().calculateOrthodoxEasterFor(Year.of(2000)))
        assertEquals(LocalDate.parse("2008-04-27"), GregorianEaster().calculateOrthodoxEasterFor(Year.of(2008)))
        assertEquals(LocalDate.parse("2009-04-19"), GregorianEaster().calculateOrthodoxEasterFor(Year.of(2009)))
        assertEquals(LocalDate.parse("2011-04-24"), GregorianEaster().calculateOrthodoxEasterFor(Year.of(2011)))
        assertEquals(LocalDate.parse("2020-04-19"), GregorianEaster().calculateOrthodoxEasterFor(Year.of(2020)))
    }

}