import org.antlr.v4.runtime.*
import io.ktor.server.application . *
import io.ktor.server.response . *
import io.ktor.server.routing . *
import io.ktor.server.engine . *
import io.ktor.server.netty . *

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

    for (line in root.line()) {
        print(line.statement().children.size)
    }

    embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
        }
    }.start(wait = true)
}