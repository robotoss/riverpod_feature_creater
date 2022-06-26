package com.github.robotoss.riverpodfeaturecreater.generator

import com.github.robotoss.riverpodfeaturecreater.generator.components.InteractorGenerator
import com.github.robotoss.riverpodfeaturecreater.generator.components.RepositoryGenerator
import com.github.robotoss.riverpodfeaturecreater.generator.components.StateManagerGenerator
import com.github.robotoss.riverpodfeaturecreater.generator.components.StatesGenerator

object FileGeneratorFactory {
    fun getFileDataGenerators(name: String): List<FileDataGenerator> {
        val repository = RepositoryGenerator(name)
        val interactor = InteractorGenerator(name)
        val states = StatesGenerator(name)
        val stateManager = StateManagerGenerator(name)
        return listOf(repository, interactor, states, stateManager)
    }
}