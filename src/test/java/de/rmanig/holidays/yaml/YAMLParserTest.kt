package de.rmanig.holidays.yaml

import org.junit.jupiter.api.Test

class YAMLParserTest {

    @Test
    fun testDE() {
        val yamlParser = YAMLParser()
        //        URL url = this.getClass().getResource("/de.yaml");
        val inputStream = this.javaClass.getResourceAsStream("/de.yaml")
        val de = yamlParser.parse(inputStream)
        val x = ""
    }

}