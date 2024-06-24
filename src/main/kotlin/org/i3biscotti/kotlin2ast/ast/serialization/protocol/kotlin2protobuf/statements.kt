package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.Statements
import protocol.statement

fun Statement.toProtobuf(): Statements.Statement {
    val stmt = this;

    return protocol.statement {

        when (stmt) {
            is VariableDeclarationStatement -> this.varDeclarationStatement = stmt.toProtobuf()
            is AssignmentStatement -> this.assignmentStatement = stmt.toProtobuf()
            is ClassDefinitionStatement -> this.classDefinitionStatement = stmt.toProtobuf()
            is ExpressionDefinitionStatement -> this.expressionDefinitionStatement = stmt.toProtobuf()
            is ForDefinitionStatement -> this.forDefinitionStatement = stmt.toProtobuf()
            is FunctionDefinitionStatement -> this.functionDefinitionStatement = stmt.toProtobuf()
            is IfDefinitionStatement -> this.ifDefinitionStatement = stmt.toProtobuf()
            is ObjectPropertyAssignmentStatement -> this.objectPropertyAssignmentStatement = stmt.toProtobuf()
            is ReturnStatement -> this.returnStatement = stmt.toProtobuf()
            is WhileDefinitionStatement -> this.whileDefinitionStatement = stmt.toProtobuf()
            is ConstructorDefinitionStatement -> throw UnsupportedOperationException()
            is AssignmentForStatement -> TODO()
            is ExpressionForStatement -> TODO()
            is VarDeclarationForStatement -> TODO()
        }

    }
}

fun VariableDeclarationStatement.toProtobuf(): Statements.VariableDeclarationStatement {
    val ast = this;

    return protocol.variableDeclarationStatement {
        name = ast.name
        varType = ast.varType.toProtobuf()

        if (ast.value != null) {
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

fun VariableType.toProtobuf(): Statements.VariableType {
    return when (this) {
        VariableType.immutable -> Statements.VariableType.IMMUTABLE
        VariableType.variable -> Statements.VariableType.VARIABLE
        VariableType.constant -> Statements.VariableType.CONSTANT
    }
}

fun AssignmentStatement.toProtobuf(): Statements.AssignmentStatement {
    val ast = this;

    return protocol.assignmentStatement {
        name = ast.name
        value = ast.value.toProtobuf()

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun ClassDefinitionStatement.toProtobuf(): Statements.ClassDefinitionStatement {
    val ast = this;

    return protocol.classDefinitionStatement {
        name = ast.name
        encapsulation =
            if (ast.isPrivate) {
                Statements.EncapsulationType.PRIVATE
            } else {
                Statements.EncapsulationType.PUBLIC
            }

        properties.addAll(ast.properties.map { it.toProtobuf() })
        constructors.addAll(ast.constructors.map { it.toProtobuf() })
        methods.addAll(ast.methods.map { it.toProtobuf() })

        if (ast.parentClassType != null) {
            parentClassType = protocol.variableValueType { name = ast.parentClassType.name }
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun PropertyDeclaration.toProtobuf(): Statements.VariableDeclarationStatement {
    val ast = this;

    return protocol.variableDeclarationStatement {
        name = ast.name
        varType = ast.varType.toProtobuf()
        valueType = protocol.variableValueType { name = ast.valueType.name }

        if (ast.value != null) {
            value = ast.value.toProtobuf()
        }

        if (ast.position != null) {
            this.position = ast.position.toProtobuf()
        }
    }
}

fun ConstructorDefinitionStatement.toProtobuf(): Statements.ConstructorDefinitionStatement {
    val ast = this;

    return protocol.constructorDefinitionStatement {
        className = ast.className
        constructorName = ast.constructorName
        parameters.addAll(ast.parameters.map { it.toProtobuf() })
        body.addAll(ast.body.map { it.toProtobuf() })

        if (ast.thisConstructor != null) {
            thisConstructor = ast.thisConstructor.toProtobuf()
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun ThisConstructorDefinition.toProtobuf(): Statements.ThisConstructorDefinition {
    val ast = this;

    return protocol.thisConstructorDefinition {
        parameters.addAll(ast.parameters.map { it.toProtobuf() })

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun Parameter.toProtobuf(): Statements.Parameter {
    val ast = this;

    return protocol.parameter {
        name = ast.name
        valueType = protocol.variableValueType { name = ast.valueType.name }
        type = when (ast.paramType) {
            ParameterType.THIS -> Statements.ParameterType.THIS
            ParameterType.SUPER -> Statements.ParameterType.SUPER
            ParameterType.TYPE -> Statements.ParameterType.TYPED
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun ObjectPropertyAssignmentStatement.toProtobuf(): Statements.ObjectPropertyAssignmentStatement {
    val ast = this;

    return protocol.objectPropertyAssignmentStatement {
        objectName = ast.objectName
        propertyName = ast.propertyName
        value = ast.value.toProtobuf()

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun ExpressionDefinitionStatement.toProtobuf(): Statements.ExpressionDefinitionStatement {
    val ast = this;

    return protocol.expressionDefinitionStatement {
        value = ast.expression.toProtobuf()

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun ForDefinitionStatement.toProtobuf(): Statements.ForDefinitionStatement {
    val ast = this;

    TODO()

    return protocol.forDefinitionStatement {


        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun FunctionDefinitionStatement.toProtobuf(): Statements.FunctionDefinitionStatement {
    val ast = this;

    return protocol.functionDefinitionStatement {
        name = ast.name
        parameters.addAll(ast.parameters.map { it.toProtobuf() })
        statements.addAll(ast.statements.map { it.toProtobuf() })

        if (ast.returnType != null) {
            returnType = protocol.variableValueType { name = ast.returnType.name }
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun IfDefinitionStatement.toProtobuf(): Statements.IfDefinitionStatement {
    val ast = this;

    return protocol.ifDefinitionStatement {
        ifBlock = ast.ifBlock.toProtobuf()

        if (ast.elseIfBlock != null) {
            elseIfBlocks.addAll(ast.elseIfBlock.map { it.toProtobuf() })
        }

        if (ast.elseBlock != null) {
            elseBlock = ast.elseBlock.toProtobuf()
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun IfBlock.toProtobuf(): Statements.IfBlock {
    val ast = this;

    return protocol.ifBlock {
        blockType = when (ast.blockType) {
            BlockType.IfBlock -> Statements.BlockType.IF_BLOCK
            BlockType.ElseIfBlock -> Statements.BlockType.ELSE_IF_BLOCK
            BlockType.ElseBlock -> Statements.BlockType.ELSE_BLOCK
        }

        if (ast.condition != null) {
            condition = ast.condition.toProtobuf()
        }

        statements.addAll(ast.statements.map { it.toProtobuf() })

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}


fun ReturnStatement.toProtobuf(): Statements.ReturnStatement {
    val ast = this;

    return protocol.returnStatement {
        if (ast.value != null) {
            value = ast.value.toProtobuf()
        }

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}

fun WhileDefinitionStatement.toProtobuf(): Statements.WhileDefinitionStatement {
    val ast = this

    return protocol.whileDefinitionStatement {
        condition = ast.whileCondition.toProtobuf()
        statements.addAll(ast.statements.map { it.toProtobuf() })

        if (ast.position != null) {
            position = ast.position.toProtobuf()
        }
    }
}