package com.github.robotoss.riverpodfeaturecreater.generator

import com.fleshgrinder.extensions.kotlin.toLowerCamelCase
import com.fleshgrinder.extensions.kotlin.toLowerSnakeCase
import com.fleshgrinder.extensions.kotlin.toUpperCamelCase
import com.google.common.io.CharStreams
import com.intellij.psi.PsiDirectory
import org.apache.commons.lang.text.StrSubstitutor
import java.io.InputStreamReader


abstract class FileDataGenerator(private val name: String,
                                 templateName: String) {

    private val TEMPLATE_FEATURE_PASCAL_CASE = "feature_pascal_case"
    private val TEMPLATE_FEATURE_SNAKE_CASE = "feature_snake_case"
    private val TEMPLATE_FEATURE_VARIABLE_CASE = "feature_variable_case"

    private val templateString: String
    private val templateValues: MutableMap<String, String>

    init {
        templateValues = mutableMapOf(
                TEMPLATE_FEATURE_PASCAL_CASE to pascalCase(),
                TEMPLATE_FEATURE_SNAKE_CASE to snakeCase(),
                TEMPLATE_FEATURE_VARIABLE_CASE to variableCase()
        )
        try {
            val resource = "/templates/$templateName.dart.template"
            val resourceAsStream = FileDataGenerator::class.java.getResourceAsStream(resource)
            templateString = CharStreams.toString(InputStreamReader(resourceAsStream!!, Charsets.UTF_8))
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    abstract fun fileName(): String

    abstract fun fileDirectory(dir: PsiDirectory): PsiDirectory

    fun generate(): String {
        val substitutor = StrSubstitutor(templateValues)
        return substitutor.replace(templateString)
    }

    private fun pascalCase(): String = name.toUpperCamelCase().replace("Feature", "")

    fun snakeCase() = name.toLowerSnakeCase().replace("_feature", "")

    fun variableCase() = name.toLowerCamelCase().replace("Feature", "")

    fun fileExtension() = "dart"
}