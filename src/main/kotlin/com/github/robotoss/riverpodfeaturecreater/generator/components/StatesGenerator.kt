package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.intellij.psi.PsiDirectory

class StatesGenerator(name: String) : FileDataGenerator(name, templateName = "states") {
    override fun fileName() = "${snakeCase()}_state.${fileExtension()}"

    override fun fileDirectory(dir: PsiDirectory) = dir.createSubdirectory("presentation").createSubdirectory("state")

}