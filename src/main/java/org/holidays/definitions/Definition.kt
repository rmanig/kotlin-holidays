package org.holidays.definitions

data class Definition(
        val name: String,
        val regions: List<String>,
        val function: String?,
        val function_modifier: Int?,
        val type: String?,
        val mday: Int?,
        val year_ranges: YearRange?)