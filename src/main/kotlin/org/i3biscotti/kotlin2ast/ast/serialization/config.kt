package org.i3biscotti.kotlin2ast.ast.serialization

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import org.i3biscotti.kotlin2ast.ast.models.*

val module = SerializersModule {
    polymorphic(Node::class) {
        subclass(ProgramFile::class)
        subclass(VarDeclarationStatement::class)
        subclass(AssignmentStatement::class)
        subclass(IntLiteralExpression::class)
        subclass(DecLit::class)
        subclass(StringLit::class)
        subclass(BooleanLitExpression::class)
        subclass(ClassDefinitionStatement::class)
    }
}

val format = Json { serializersModule = module }