package com.github.robotoss.riverpodfeaturecreater.generator.components

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.intellij.psi.PsiDirectory

class StateManagerGenerator(name: String) : FileDataGenerator(name, templateName = "state_manager") {
    override fun fileName() = "${snakeCase()}_state_manager.${fileExtension()}"

    override fun fileDirectory(dir: PsiDirectory) = dir.findSubdirectory("presentation")!!.findSubdirectory("state")!!

}