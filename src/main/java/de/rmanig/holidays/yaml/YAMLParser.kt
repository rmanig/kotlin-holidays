package de.rmanig.holidays.yaml

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import de.rmanig.holidays.definitions.DefinitionFile
import java.io.IOException
import java.io.InputStream

class YAMLParser {

    private val objectMapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)


    fun parse(inputStream: InputStream?): DefinitionFile {
        val definitionFile: DefinitionFile = try {
            objectMapper.readValue(inputStream, DefinitionFile::class.java)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
        return definitionFile
    }

}