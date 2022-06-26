package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator

class RepositoryGenerator(name: String) : FileDataGenerator(name, templateName = "repository") {
    override fun fileName() = "${snakeCase()}_repository.${fileExtension()}"
}