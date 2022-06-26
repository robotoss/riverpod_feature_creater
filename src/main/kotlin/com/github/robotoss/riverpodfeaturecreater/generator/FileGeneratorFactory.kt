package com.github.robotoss.riverpodfeaturecreater.generator

import com.github.robotoss.riverpodfeaturecreater.generator.components.RepositoryGenerator

object FileGeneratorFactory {
    fun getFileDataGenerators(name: String,): List<FileDataGenerator> {
        val repository = RepositoryGenerator(name)
        return listOf(repository,)
    }
}