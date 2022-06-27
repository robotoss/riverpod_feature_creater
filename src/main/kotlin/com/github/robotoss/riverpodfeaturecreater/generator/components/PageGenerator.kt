package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.intellij.psi.PsiDirectory

class PageGenerator(name: String) : FileDataGenerator(name, templateName = "page") {
    override fun fileName() = "${snakeCase()}_page.${fileExtension()}"

    override fun fileDirectory(dir: PsiDirectory) = dir.findSubdirectory("presentation")!!.createSubdirectory("ui")

}