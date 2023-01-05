package org.holidays.definitions

data class DefinitionFile(val months: Map<Int, List<Definition>>,
                          val methods: Map<String, Method>,
                          val tests: List<Test>)