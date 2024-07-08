package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import com.google.protobuf.GeneratedMessage
import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.parser.models.AntlrValidationError
import org.i3biscotti.kotlin2ast.parser.models.LangError
import org.i3biscotti.kotlin2ast.validation.models.AstValidationError
import protocol.PositionOuterClass

fun Node.toProtobuf() : GeneratedMessage{
    return when(this) {
        is ProgramFile -> this.toProtobuf()
        is Statement -> this.toProtobuf()
        is Expression -> this.toProtobuf()
        else -> throw IllegalArgumentException("Unknown node type")
    }
}

fun LangError.toProtobuf(): protocol.Response.LanguageError {
    val builder = protocol.Response.LanguageError.newBuilder()

    when (this) {
        is AntlrValidationError -> builder.setMessage(this.message)
        is AstValidationError -> builder.setMessage(this.message)
        else -> throw IllegalArgumentException("Unknown error type")
    }

    builder.position = this.position?.toProtobuf()

    return builder.build()
}

fun ProgramFile.toProtobuf(): protocol.Base.ProgramFile {
    val protoLines = this.lines.map { it.toProtobuf() }
    val protoPosition = this.position?.toProtobuf()

    return protocol.programFile {
        lines.addAll(protoLines)
        if (protoPosition != null) {
            position = protoPosition
        }
    }
}

fun Point.toProtobuf(): PositionOuterClass.Point {
    val point = this

    return protocol.point {
        line = point.line
        column = point.column
    }
}

fun Position.toProtobuf(): PositionOuterClass.Position {
    val protoStartPosition = this.start.toProtobuf()
    val protoEndPosition = this.end.toProtobuf()

    return protocol.position {
        start = protoStartPosition
        end = protoEndPosition
    }

}


