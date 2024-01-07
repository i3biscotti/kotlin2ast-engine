import org.antlr.v4.runtime.*
import io.ktor.server.application . *
import io.ktor.server.response . *
import io.ktor.server.routing . *
import io.ktor.server.engine . *
import io.ktor.server.netty . *
import org.i3biscotti.kotlin2ast.ast.serialize
import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.i3biscotti.kotlin2ast.transpiler.transpile

fun main(args: Array<String>) {

    val code = """
        | val a : Int = 2
        | const b : String = "Ciaooo"
        | c = 2.3
    """.trimMargin()

    val result = KotlinParser.parse(code)

    if (result.errors.isEmpty()) {
        println(
            """
            |The AST is:
            |${result.root?.serialize()}
            |
            |The transpiled code is:
            |${result.root?.transpile()}
            """.trimMargin()
        )
    } else {
        // Everything after this is in red
        val red = "\u001b[31m"

    // Resets previous color codes
        val reset = "\u001b[0m"
        println(
            """$red
               ${result.errors.joinToString("\n")}
               $reset
            """.trimIndent()
        )

    }


//    embeddedServer(Netty, port = 8080) {
//        routing {
//            get("/") {
//                call.respondText("Hello, world!")
//            }
//        }
//    }.start(wait = true)
}