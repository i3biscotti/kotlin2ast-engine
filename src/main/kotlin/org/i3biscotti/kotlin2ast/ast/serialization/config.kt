package org.i3biscotti.kotlin2ast.ast.serialization

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import org.i3biscotti.kotlin2ast.ast.*

val module = SerializersModule {
    polymorphic(Node::class) {
        subclass(ProgramFile::class)
        subclass(VarDeclarationStatement::class)
        subclass(AssignmentStatement::class)
        subclass(IntLit::class)
        subclass(DecLit::class)
        subclass(StringLit::class)
        subclass(BooleanLit::class)
    }
}

val format = Json { serializersModule = module }