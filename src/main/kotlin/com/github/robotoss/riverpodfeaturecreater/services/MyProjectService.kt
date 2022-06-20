package com.github.robotoss.riverpodfeaturecreater.services

import com.intellij.openapi.project.Project
import com.github.robotoss.riverpodfeaturecreater.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
