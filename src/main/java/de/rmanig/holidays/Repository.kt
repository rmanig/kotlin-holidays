package de.rmanig.holidays

import de.rmanig.holidays.definitions.DefinitionFile
import de.rmanig.holidays.definitions.Definition
import de.rmanig.holidays.functions.GregorianEaster
import de.rmanig.holidays.functions.RegionalFunction
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
