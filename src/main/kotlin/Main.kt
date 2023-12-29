
import org.antlr.v4.runtime.*

fun main(args: Array<String>) {
    val input = CharStreams.fromString("""
        | val a : Int = 2
        | val b : Int = "Ciaooo"
    """.trimMargin())

    val lexer = kotlinLexer(input)

    //val tSequences = lexer.allTokens.joinToString(separator = " ") {lexer.ruleNames[it.type - 1] }
    //println(tSequences)

    val tokens = CommonTokenStream(lexer)
    val parser = kotlinParser(tokens)

    val root = parser.kotlinFile()

    for ( line in root.line() ){
        print(line.statement().children.size)
    }
}