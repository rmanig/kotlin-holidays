package org.holidays

import java.time.LocalDate

data class Holiday(val name: String,
                   val date: LocalDate,
                   val informal: Boolean,
                   val region: List<String>)
