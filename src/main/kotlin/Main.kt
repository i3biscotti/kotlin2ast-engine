import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.i3biscotti.kotlin2ast.ast.serialization.format
import org.i3biscotti.kotlin2ast.networking.models.*
import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.i3biscotti.kotlin2ast.transpiler.transpile

fun main(args: Array<String>) {

    val a : List<Any> = listOf(1,2,"3")


    embeddedServer(Netty, port = 8080) {
        routing {
            post("/transpile") {
                val transpileReqText = call.receiveText()
                val transpileReq = Json.decodeFromString<TranspileRequest>(transpileReqText)

                val transpiledCode = transpileReq.ast.transpile()
                call.respondText { transpiledCode }
            }

            post("/ast") {
                val code = call.receiveText()
                val parsingResult = KotlinParser.parse(code)

                val apiResponse = AstResponse(parsingResult.root!!)

                call.respondText {
                    format.encodeToString(apiResponse)
                }
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