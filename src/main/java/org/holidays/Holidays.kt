package org.holidays

import org.holidays.definitions.Definition
import org.holidays.definitions.Method
import java.nio.file.WatchEvent.Modifier
import java.time.LocalDate
import java.time.Year
import java.time.YearMonth
import kotlin.math.sign

class Holidays {

    private val repository: Repository = Repository()

    init {
        val loader = Loader()
        val definitions = loader.loadAll()
        definitions.stream().forEach { repository.add(it) }
    }

    fun on(date: LocalDate, options: Options = Options()) : List<Holiday> {
        return between(date, date, options)
    }

    fun between(from: LocalDate, to: LocalDate, options: Options = Options()) : List<Holiday> {
        if (from.isAfter(to)) {
            throw IllegalArgumentException("from cannot be after to!")
        }

        val holidays: MutableList<Holiday> = mutableListOf()

        val yearMonths = getMonthsBetween(from, to)
        val visitedYears = mutableSetOf<Year>()
        for (yearMonth in yearMonths) {
            var definitions = repository.definitionsByMonth.getOrDefault(yearMonth.monthValue, listOf())

            val year = Year.of(yearMonth.year)
            if (!visitedYears.contains(year)) {
                // this works assuming the specific month of the YearMonth never matters for the (easter?) functions
                // from month "0" in the definition file.
                definitions = definitions + repository.definitionsByMonth.getOrDefault(0, listOf())
                visitedYears.add(year)
            }

            for (definition in definitions) {

                // todo observed
                val informal = "informal" == definition.type
                if (!options.informal && informal) {
                    continue
                }

                // todo region filter

                val date = buildDate(yearMonth, definition)

                if (date.isBefore(from) || date.isAfter(to)) {
                    continue
                }

                val holiday = Holiday(definition.name, date, informal, definition.regions)
                holidays.add(holiday)

            }
        }
        return holidays
    }

    private fun buildDate(yearMonth: YearMonth, definition: Definition) : LocalDate {
        return if (definition.function != null) {
            dateFromFunction(yearMonth, definition.function, (definition.function_modifier ?: 0).toLong())

        } else if (definition.mday != null) {
            definition.mday.let { yearMonth.atDay(it)}

        } else {
            // todo wday etc
            LocalDate.MIN
        }
    }

    private fun dateFromFunction(yearMonth: YearMonth, signature: String, modifier: Long) : LocalDate {
        val (name, args) = signature.replace(")", "").split("(")
        val arguments: Map<String, Any> = args.split(", ")
                .associateBy({it}, { if (it == "year") yearMonth.year else "" })
        val function = repository.functions[name]
        return function?.invoke(arguments)?.plusDays(modifier)
                ?: throw IllegalStateException("Function $name is missing!")
    }

    private fun getMonthsBetween(from: LocalDate, to: LocalDate, addBorderMonths: Boolean = true) : List<YearMonth> {
        var current = YearMonth.from(from)
        val until = YearMonth.from(to)
        val months = mutableListOf<YearMonth>()
        while (!current.isAfter(until)) {
            months.add(current)
            current = current.plusMonths(1)
        }

        if (addBorderMonths) {
            months.add(0, YearMonth.from(from).minusMonths(1))
            months.add(until.plusMonths(1))
        }

        return months
    }

}