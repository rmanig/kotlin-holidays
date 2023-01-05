package org.holidays

import org.holidays.definitions.DefinitionFile
import org.holidays.yaml.YAMLParser

class Loader {

    private val yamlParser = YAMLParser()

    // todo
    fun loadAll(): List<DefinitionFile> {
        val inputStream = this.javaClass.getResourceAsStream("/de.yaml")
        val de = yamlParser.parse(inputStream)
        return listOf(de)
    }

}