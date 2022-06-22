package com.github.robotoss.riverpodfeaturecreater.actions

import com.intellij.openapi.actionSystem.*

class MyApplicationAction : AnAction()  {

    private lateinit var dataContext: DataContext
    override fun actionPerformed(e: AnActionEvent) {
        val wrapper = GenerateFeatureDialog(e.project!!)

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