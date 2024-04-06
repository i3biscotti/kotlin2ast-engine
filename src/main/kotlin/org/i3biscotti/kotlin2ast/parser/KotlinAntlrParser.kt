package org.i3biscotti.kotlin2ast.parser

import KotlinParser
import KotlinLexer
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.*
import org.antlr.v4.runtime.dfa.DFA
import org.i3biscotti.kotlin2ast.ast.models.Point
import org.i3biscotti.kotlin2ast.parser.models.AntlrValidationError
import org.i3biscotti.kotlin2ast.parser.models.LangError
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*

data class AntlrParsingResult(val root: KotlinParser.KotlinFileContext?, val errors: List<AntlrValidationError>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

fun String.toStream(charset: Charset = Charsets.UTF_8) =
    ByteArrayInputStream(toByteArray(charset))

object KotlinAntlrParser {

    fun parse(inputStream: InputStream): AntlrParsingResult {
        val lexicalAndSyntacticErrors = LinkedList<AntlrValidationError>()
        val errorListener = object : ANTLRErrorListener {
            override fun reportAmbiguity(
                p0: Parser?,
                p1: DFA?,
                p2: Int,
                p3: Int,
                p4: Boolean,
                p5: BitSet?,
                p6: ATNConfigSet?
            ) {
            }

            override fun reportAttemptingFullContext(
                p0: Parser?,
                p1: DFA?,
                p2: Int,
                p3: Int,
                p4: BitSet?,
                p5: ATNConfigSet?
            ) {
            }

            override fun syntaxError(
                recognizer: Recognizer<*, *>?,
                offendingSymbol: Any?,
                line: Int,
                charPositionInline: Int,
                msg: String,
                ex: RecognitionException?
            ) {
                lexicalAndSyntacticErrors.add(AntlrValidationError(msg, Point(line, charPositionInline)))
            }

            override fun reportContextSensitivity(
                p0: Parser?,
                p1: DFA?,
                p2: Int,
                p3: Int,
                p4: Int,
                p5: ATNConfigSet?
            ) {
            }
        }

        val lexer = KotlinLexer(CharStreams.fromStream(inputStream))
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)

        val tokens = CommonTokenStream(lexer)
        val parser = KotlinParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)

        return AntlrParsingResult(parser.kotlinFile(), lexicalAndSyntacticErrors)
    }
}