package org.holidays

import org.holidays.definitions.DefinitionFile
import org.holidays.definitions.Definition
import org.holidays.functions.GregorianEaster
import org.holidays.functions.RegionalFunction
import java.time.LocalDate
import java.time.Year

class Repository {

    val definitionsByMonth: MutableMap<Int, MutableList<Definition>> = LinkedHashMap()
    val functions: MutableMap<String, (Map<String, Any>) -> LocalDate> = LinkedHashMap()
    val regions: MutableMap<String, String> = LinkedHashMap()

    init {
        addGlobalFunctions()
    }

    fun add(definitionFile: DefinitionFile) {
        for (month in definitionFile.months) {
            val existing = definitionsByMonth.getOrDefault(month.key, mutableListOf())
            val new = month.value
            existing.addAll(new)
            definitionsByMonth.put(month.key, existing)

            val newRegions = mutableSetOf<String>()
            for (definition in new) {
                newRegions.addAll(definition.regions)
            }
            for (region in newRegions) {
                regions.putIfAbsent(region, region)
            }
        }

        for (method in definitionFile.methods) {
            val functionName = method.key
//            val functionArgs = entry.value.arguments
            try {
                val regionalFunction = RegionalFunction.valueOf(functionName)
                functions[functionName] = { regionalFunction.apply(it) }
            } catch (iae: IllegalArgumentException) {
                throw RuntimeException("The given function $functionName could not be found. " +
                        "Make sure it is already implemented!")
            }
        }
    }

    private fun addGlobalFunctions() {
        functions["easter"] = { args ->
            val year: Int = (args["year"] ?: throw IllegalArgumentException("year is missing!")) as Int
            GregorianEaster.calculateEasterFor(Year.of(year))
        }
    }

}
