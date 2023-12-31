@file:Suppress("UNCHECKED_CAST")

package org.i3biscotti.kotlin2ast.parser

import org.i3biscotti.kotlin2ast.ast.KotlinFile
import org.i3biscotti.kotlin2ast.ast.Node
import org.i3biscotti.kotlin2ast.ast.process
import org.i3biscotti.kotlin2ast.validation.validate
import java.io.*

fun <T : Node> Node.specificProcess(klass: Class<T>, operation: (T) -> Unit){
    process {if (klass.isInstance(it)) { operation(it as T) }  }
}

data class KotlinParsingResult(val root: KotlinFile?, val errors: List<KotlinLangError>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

object KotlinParser {
    fun parse(code: String): KotlinParsingResult = KotlinParser.parse(code.toStream())

    fun parse(file: File): KotlinParsingResult = KotlinParser.parse(FileInputStream(file))

    fun parse(inputStream: InputStream): KotlinParsingResult {
        val parsingResult = KotlinAntlrParser.parse(inputStream)
        return if (parsingResult.isCorrect()) {

            val kotlinFile: KotlinFile = parsingResult.root.toAst()
            val semanticErrors = kotlinFile.validate()

            KotlinParsingResult(kotlinFile, parsingResult.errors + semanticErrors)
        } else {
            KotlinParsingResult(null, parsingResult.errors)
        }
    }

}