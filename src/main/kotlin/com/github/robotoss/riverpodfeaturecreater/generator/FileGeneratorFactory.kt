package com.github.robotoss.riverpodfeaturecreater.generator

import com.github.robotoss.riverpodfeaturecreater.generator.components.*

object FileGeneratorFactory {
    fun getFileDataGenerators(name: String): List<FileDataGenerator> {
        val repository = RepositoryGenerator(name)
        val interactor = InteractorGenerator(name)
        val states = StatesGenerator(name)
        val stateManager = StateManagerGenerator(name)
        val page = PageGenerator(name)
        return listOf(repository, interactor, states, stateManager, page)
    }
}