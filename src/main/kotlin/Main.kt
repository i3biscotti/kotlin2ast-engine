import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


import org.i3biscotti.kotlin2ast.networking.models.*
import org.i3biscotti.kotlin2ast.parser.KotlinParser

fun main(args: Array<String>) {

    val a : List<Any> = listOf(1,2,"3")


    embeddedServer(Netty, port = 8080) {
        routing {
            post("/transpile") {

                call.respondText { "Hello, World!" }
            }

            post("/ast") {
                val code = call.receiveText()
                val parsingResult = KotlinParser.parse(code)

                val apiResponse = AstResponse(parsingResult.root!!)

                call.respondText {
"Hello"                }
                /*
                * call.respondText {
                    if (parsingResult.isCorrect()) {
                        format.encodeToString(parsingResult.root)
                    }
                    else {
                        format.encodeToString(
                            mapOf("errors" to parsingResult.errors)
                        )
                   */

            }
        }
    }.start(wait = true)
}