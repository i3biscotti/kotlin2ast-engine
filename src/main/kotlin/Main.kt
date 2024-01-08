import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.i3biscotti.kotlin2ast.transpiler.transpile

fun main(args: Array<String>) {

    val code = """
        | val a : Int = 2
        | const b : String = "Ciaooo"
        | var c = 2.3
    """.trimMargin()

    val result = KotlinParser.parse(code)

    if (result.errors.isEmpty()) {
        println(
            """
            |The AST is:
            |${Json.encodeToString(result.root)}
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
            """
           |$red
           |${result.errors.joinToString("\n")}
           |$reset
           """.trimMargin()
        )

    }


    embeddedServer(Netty, port = 8080) {
        routing {
            post("/compile") {
               val compileReqText = call.receiveText()
            }

            post("/ast"){
                val astReqText = call.receiveText()
            }
        }
    }.start(wait = true)
}