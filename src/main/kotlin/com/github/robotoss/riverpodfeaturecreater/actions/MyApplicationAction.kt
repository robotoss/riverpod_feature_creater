package com.github.robotoss.riverpodfeaturecreater.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DataContext

class MyApplicationAction : AnAction() {

    private lateinit var dataContext: DataContext

//    fun onGenerateFeatureClicked(blocName: String?) {
//        blocName?.let { name ->
//            val generators = FileGeneratorFactory.getFileDataGenerators(name)
//            generate(generators)
//        }
//    }


    override fun actionPerformed(e: AnActionEvent) {
        val wrapper = GenerateFeatureDialog(e)

        if (wrapper.showAndGet()) {
            println("Successfully handled user input")
        }
    }

    override fun update(e: AnActionEvent) {
        e.dataContext.let {
            this.dataContext = it
            val presentation = e.presentation
            presentation.isEnabled = true
        }

    }
}