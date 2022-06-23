package com.github.robotoss.riverpodfeaturecreater.actions

import com.intellij.notification.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
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

class GenerateFeatureDialog (private val project: Project) : DialogWrapper(true) {

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
        NotificationManager.createAndShowNotification(project, className.text, )
        super.doOKAction()
    }

    class NotificationManager {
        companion object {
            @JvmStatic
            fun createAndShowNotification(project: Project, className: String,) {
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