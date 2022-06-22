package com.github.robotoss.riverpodfeaturecreater.actions

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class MyApplicationAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        BrowserUtil.browse("https://www.10bis.co.il/")
    }

    override fun update(e: AnActionEvent) {
        super.update(e)

    }
}