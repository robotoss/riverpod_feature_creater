package com.github.robotoss.riverpodfeaturecreater.actions

import com.github.robotoss.riverpodfeaturecreater.generator.FileDataGenerator
import com.github.robotoss.riverpodfeaturecreater.generator.FileGeneratorFactory
import com.intellij.notification.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.CommandProcessor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFileFactory
import com.intellij.ui.components.JBLabel
import com.intellij.uiDesigner.core.AbstractLayout
import com.intellij.util.ui.GridBag
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import java.awt.Dimension
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField
import com.intellij.lang.java.JavaLanguage

class GenerateFeatureDialog(private val e: AnActionEvent) : DialogWrapper(true) {

    private val className: JTextField = JTextField()

    private val centerPanel: JPanel = JPanel(GridBagLayout())

    init {
        init()
        title = "Create New Feature"
    }

    override fun createCenterPanel(): JComponent? {
        val gridbag = GridBag()
            .setDefaultWeightX(1.0)
            .setDefaultFill(GridBagConstraints.HORIZONTAL)
            .setDefaultInsets(Insets(0, 0, AbstractLayout.DEFAULT_VGAP, AbstractLayout.DEFAULT_HGAP))
        centerPanel.preferredSize = Dimension(400, 50)

        centerPanel.add(getLabel("Class Name: "), gridbag.nextLine().next().weightx(0.2))
        centerPanel.add(className, gridbag.next().weightx(0.8))

        return centerPanel
    }

    private fun getLabel(text: String): JComponent {
        val label = JBLabel(text)
        label.componentStyle = UIUtil.ComponentStyle.SMALL
        label.fontColor = UIUtil.FontColor.BRIGHTER
        label.border = JBUI.Borders.empty(0, 5, 2, 0)
        return label
    }

    override fun doOKAction() {
        NotificationManager.createAndShowNotification(e.project!!, className.text)

        className.text?.let { name ->
            val generators = FileGeneratorFactory.getFileDataGenerators(name)
            val project = CommonDataKeys.PROJECT.getData(e.dataContext)
            val view = LangDataKeys.IDE_VIEW.getData(e.dataContext)
            val directory = view?.orChooseDirectory
            ApplicationManager.getApplication().runWriteAction {
                CommandProcessor.getInstance().executeCommand(
                    project,
                    {
                        generators.forEach { createSourceFile(project!!, it, directory!!) }
                    },
                    "Generate a new Bloc",
                    null
                )

            }

        }


        super.doOKAction()
    }

    private fun createSourceFile(project: Project, generator: FileDataGenerator, directory: PsiDirectory) {
        val fileName = generator.fileName()
        val existingPsiFile = directory.findFile(fileName)
        if (existingPsiFile != null) {
            val document = PsiDocumentManager.getInstance(project).getDocument(existingPsiFile)
            document?.insertString(document.textLength, "\n" + generator.generate())
            return
        }

        val factory = PsiFileFactory.getInstance(project)
        val psiFile = factory.createFileFromText(fileName, JavaLanguage.INSTANCE, generator.generate())

        directory.add(psiFile)
    }


    class NotificationManager {
        companion object {
            @JvmStatic
            fun createAndShowNotification(project: Project, className: String) {
                val notification = NotificationGroup("AndroidVille", NotificationDisplayType.BALLOON, true)
                notification.createNotification(
                    "Success!",
                    "Your class name is $className ",
                    NotificationType.INFORMATION,
                    null
                ).notify(project)
            }
        }
    }

}