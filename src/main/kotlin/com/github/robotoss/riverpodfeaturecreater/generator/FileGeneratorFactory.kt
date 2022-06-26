package com.github.robotoss.riverpodfeaturecreater.generator

import com.github.robotoss.riverpodfeaturecreater.generator.components.InteractorGenerator
import com.github.robotoss.riverpodfeaturecreater.generator.components.RepositoryGenerator

object FileGeneratorFactory {
    fun getFileDataGenerators(name: String): List<FileDataGenerator> {
        val repository = RepositoryGenerator(name)
        val interactor = InteractorGenerator(name)
        return listOf(repository, interactor)
    }
}