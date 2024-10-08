package org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.positionOrNull


fun protocol.Base.ProgramFile.toAst(): ProgramFile {
    return ProgramFile(
        lines = linesList.map { it.toAst() },
        positionOrNull?.toAst()
    )
}

fun protocol.PositionOuterClass.Position.toAst(): Position {
    return Position(
        start = this.start.toAst(),
        end = this.end.toAst()
    )
}

fun protocol.PositionOuterClass.Point.toAst(): Point {
    return Point(
        line = this.line,
        column = this.column
    )
}