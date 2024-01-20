@file:Suppress("UNCHECKED_CAST")

package org.i3biscotti.kotlin2ast.parser

import org.i3biscotti.kotlin2ast.ast.*
import org.i3biscotti.kotlin2ast.ast.models.Node
import org.i3biscotti.kotlin2ast.ast.models.ProgramFile
import org.i3biscotti.kotlin2ast.validation.validate
import java.io.*

fun <T : Node> Node.specificProcess(klass: Class<T>, operation: (T) -> Unit) {
    process {
        if (klass.isInstance(it)) {
            operation(it as T)
        }
    }
}

data class KotlinParsingResult(val root: ProgramFile?, val errors: List<KotlinLangError>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

object KotlinParser {
    fun parse(code: String, considerPosition: Boolean = true): KotlinParsingResult =
        parse(code.toStream(), considerPosition)

    fun parse(file: File, considerPosition: Boolean = true): KotlinParsingResult =
        parse(FileInputStream(file), considerPosition)

    fun parse(inputStream: InputStream, considerPosition: Boolean = true): KotlinParsingResult {
        val parsingResult = KotlinAntlrParser.parse(inputStream)
        return if (parsingResult.isCorrect()) {

            val programFile: ProgramFile = parsingResult.root!!.toAst(considerPosition)
            val semanticErrors = programFile.validate()

            KotlinParsingResult(programFile, parsingResult.errors + semanticErrors)
        } else {
            KotlinParsingResult(null, parsingResult.errors)
        }
    }

}