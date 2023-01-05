package de.rmanig.holidays

import de.rmanig.holidays.definitions.DefinitionFile
import de.rmanig.holidays.yaml.YAMLParser

class Loader {

    private val yamlParser = YAMLParser()

    // todo
    fun loadAll(): List<DefinitionFile> {
        val inputStream = this.javaClass.getResourceAsStream("/de.yaml")
        val de = yamlParser.parse(inputStream)
        return listOf(de)
    }

}