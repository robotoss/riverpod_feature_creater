package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.intellij.psi.PsiDirectory

class InteractorGenerator(name: String) : FileDataGenerator(name, templateName = "interactor") {
    override fun fileName() = "${snakeCase()}_interactor.${fileExtension()}"

    override fun fileDirectory(dir: PsiDirectory) = dir.createSubdirectory("domain")

}