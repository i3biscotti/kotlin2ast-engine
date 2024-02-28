package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import org.i3biscotti.kotlin2ast.ast.models.*

fun Statement.toProtobuf(): protocol.Statements.Statement {
    val stmt = this;

    return protocol.statement {

        when (stmt) {
            is VariableDeclarationStatement -> this.varDeclarationStatement = stmt.toProtobuf()
            is AssignmentStatement -> TODO()
            is ClassDefinitionStatement -> TODO()
            is ConstructorDefinitionStatement -> TODO()
            is ExpressionDefinitionStatement -> TODO()
            is ForDefinitionStatement -> TODO()
            is FunctionDefinitionStatement -> TODO()
            is IfDefinitionStatement -> TODO()
            is ObjectPropertyAssignmentStatement -> TODO()
            is ReturnStatement -> TODO()
            is WhileDefinitionStatement -> TODO()
        }


    }

}

fun VariableDeclarationStatement.toProtobuf(): protocol.Statements.VariableDeclarationStatement {
    val ast = this;

    return protocol.variableDeclarationStatement {
        name = ast.name
        varType = ast.varType.toProtobuf()

        if(ast.value != null) {
            value = ast.value.toProtobuf()
        }

        if (ast.valueType != null) {
            valueType = protocol.variableValueType { name = ast.valueType.name }
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun VariableType.toProtobuf(): protocol.Statements.VariableType {
    return when (this) {
        VariableType.immutable -> protocol.Statements.VariableType.IMMUTABLE
        VariableType.variable -> protocol.Statements.VariableType.VARIABLE
        VariableType.constant -> protocol.Statements.VariableType.CONSTANT
    }
}