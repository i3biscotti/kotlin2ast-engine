package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import com.google.protobuf.GeneratedMessageV3
import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.PositionOuterClass

fun Node.toProtobuf() : GeneratedMessageV3{
    return when(this) {
        is ProgramFile -> this.toProtobuf()
        is Statement -> this.toProtobuf()
        is Expression -> this.toProtobuf()
        else -> throw IllegalArgumentException("Unknown node type")
    }
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


