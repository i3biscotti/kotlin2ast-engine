import com.google.protobuf.util.JsonFormat
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf.toProtobuf
import org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin.toAst

import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.i3biscotti.kotlin2ast.transpiler.transpile
import protocol.Request
import protocol.Request.GenerateAstRequest
import protocol.Response
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt

fun main(args: Array<String>) {

    embeddedServer(Netty, port = 8080) {
        routing {
            post("/generate-code") {
                val jsonRequest = call.receiveText()
                val requestBuilder = Request.GenerateCodeRequest.newBuilder()
                JsonFormat.parser().merge(jsonRequest, requestBuilder)
                val request = requestBuilder.build()


                val responseBuilder = Response.GenerateCodeResponse.newBuilder()

                try{
                    val programFile = request.ast.toAst()
                    val code = programFile.transpile()

                    responseBuilder.success = true
                    responseBuilder.code = code
                }catch(e: Exception){
                    responseBuilder.success = false
                    responseBuilder.errorsList.add(
                        Response.LanguageError.newBuilder().setMessage(e.message ?: "").build()
                    )
                }


                val response = responseBuilder.build()
                val format = JsonFormat.printer()
                call.respondText { format.print(response) }
            }

            post("/generate-ast") {
                val jsonRequest = call.receiveText()
                val requestBuilder =GenerateAstRequest.newBuilder()
                JsonFormat.parser().merge(jsonRequest, requestBuilder )
                val request = requestBuilder.build()

                val parsingResult = KotlinParser.parse(request.code)

                val responseBuilder = Response.GenerateAstResponse.newBuilder()

                try{
                    if(parsingResult.isCorrect()){
                        responseBuilder.success = true
                        responseBuilder.ast = parsingResult.root?.toProtobuf()
                    }else{
                        responseBuilder.success = false
                        val errors = parsingResult.errors.map { it.toProtobuf() }
                        responseBuilder.addAllErrors(errors)
                    }

                    val response = responseBuilder.build()
                    val format = JsonFormat.printer()
                    call.respondText { format.print(response) }
                }catch (e: Exception){
                    val response = protocol.generateAstResponse {
                        success = false
                        errors.add(
                            protocol.languageError {  message = e.message ?: "Dio cane"  }
                        )
                    }

                    val format = JsonFormat.printer()
                    call.respondText { format.print(response) }
                }
            }
        }
    }.start(wait = true)
}