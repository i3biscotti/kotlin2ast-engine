package org.i3biscotti.kotlin2ast.parser

import kotlinLexer
import kotlinParser
import kotlinParser.*
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.atn.*
import org.antlr.v4.runtime.dfa.DFA
import org.i3biscotti.kotlin2ast.ast.*
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.nio.charset.Charset
import java.util.*

data class AntlrParsingResult(val root: KotlinFileContext?, val errors: List<KotlinLangError>) {
    fun isCorrect() = errors.isEmpty() && root != null
}

fun String.toStream(charset: Charset = Charsets.UTF_8) =
    ByteArrayInputStream(toByteArray(charset))

object KotlinAntlrParser {

    fun parse(inputStream: InputStream): AntlrParsingResult {
        val lexicalAndSyntacticErrors = LinkedList<KotlinLangError>()
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
                lexicalAndSyntacticErrors.add(KotlinLangError(msg, Point(line, charPositionInline)))
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

        val lexer = kotlinLexer(CharStreams.fromStream(inputStream))
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)

        val tokens = CommonTokenStream(lexer)
        val parser = kotlinParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)

        return AntlrParsingResult(parser.kotlinFile(), lexicalAndSyntacticErrors)
    }
}