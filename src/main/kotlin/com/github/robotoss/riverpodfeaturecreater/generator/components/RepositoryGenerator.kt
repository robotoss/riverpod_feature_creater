package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.intellij.psi.PsiDirectory

class RepositoryGenerator(name: String) : FileDataGenerator(name, templateName = "repository") {
    override fun fileName() = "${snakeCase()}_repository.${fileExtension()}"

    override fun fileDirectory(dir: PsiDirectory) = dir.createSubdirectory("data")

}