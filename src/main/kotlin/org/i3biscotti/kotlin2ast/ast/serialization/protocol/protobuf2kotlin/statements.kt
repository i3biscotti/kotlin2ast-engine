package org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.Statements

fun Statements.Statement.toAst(): Statement {

    if (this.hasVarDeclarationStatement()) {
        return varDeclarationStatement.toAst()
    } else if (this.hasReturnStatement()) {
        return returnStatement.toAst()
    } else if (this.hasAssignmentStatement()) {
        return assignmentStatement.toAst()
    } else if (this.hasIfDefinitionStatement()) {
        return ifDefinitionStatement.toAst()
    } else if (this.hasExpressionDefinitionStatement()) {
        return expressionDefinitionStatement.toAst()
    } else if (this.hasForDefinitionStatement()) {
        return forDefinitionStatement.toAst()
    } else if (this.hasObjectPropertyAssignmentStatement()) {
        return objectPropertyAssignmentStatement.toAst()
    } else if (this.hasClassDefinitionStatement()) {
        return ifDefinitionStatement.toAst()
    } else if (this.hasFunctionDefinitionStatement()) {
        return functionDefinitionStatement.toAst()
    } else if(this.hasWhileDefinitionStatement()) {
        return whileDefinitionStatement.toAst()
    } else {
        throw UnsupportedOperationException()
    }

}

fun Statements.VariableDeclarationStatement.toAst(): VariableDeclarationStatement {
    val proto = this

    return VariableDeclarationStatement(
        name = proto.name,
        varType = proto.varType.toAst(),
        value = proto.value?.toAst(),
        valueType = VariableValueType(proto.valueType.name),
        position = proto.position?.toAst()
    )
}

fun Statements.VariableType.toAst(): VariableType {
    return when (this) {
        Statements.VariableType.IMMUTABLE -> VariableType.immutable
        Statements.VariableType.VARIABLE -> VariableType.variable
        Statements.VariableType.CONSTANT -> VariableType.constant
        else -> throw UnsupportedOperationException()
    }
}

fun Statements.AssignmentStatement.toAst(): AssignmentStatement {
    val proto = this

    return AssignmentStatement(
        name = proto.name,
        value = proto.value.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.ReturnStatement.toAst(): ReturnStatement {
    val proto = this

    return ReturnStatement(
        value = proto.value.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.IfDefinitionStatement.toAst(): IfDefinitionStatement {
    val proto = this

    return IfDefinitionStatement(
        ifBlock = proto.ifBlock.toAst(),
        elseIfBlock = proto.elseIfBlocksList.map { it.toAst() },
        elseBlock = proto.elseBlock.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.ExpressionDefinitionStatement.toAst(): ExpressionDefinitionStatement {
    val proto = this

    return ExpressionDefinitionStatement(
        expression = proto.value.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.ForDefinitionStatement.toAst(): ForDefinitionStatement {
    val proto = this

    TODO()
}

fun Statements.ObjectPropertyAssignmentStatement.toAst(): ObjectPropertyAssignmentStatement {
    val proto = this

    return ObjectPropertyAssignmentStatement(
        objectName = proto.objectName,
        propertyName = proto.propertyName,
        value = proto.value.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.ClassDefinitionStatement.toAst(): ClassDefinitionStatement {
    val proto = this

    return ClassDefinitionStatement(
        name = proto.name,
        isPrivate = proto.encapsulation == Statements.EncapsulationType.PRIVATE,
        properties = proto.propertiesList.map { it.toPropertyAst() },
        methods = proto.methodsList.map { it.toAst() },
        constructors = proto.constructorsList.map { it.toAst() },
        parentClassType = VariableValueType(proto.parentClassType.name),
        position = proto.position.toAst()
    )
}

fun Statements.VariableDeclarationStatement.toPropertyAst(): PropertyDeclaration {
    val proto = this

    return PropertyDeclaration(
        name = proto.name,
        varType = proto.varType.toAst(),
        value = proto.value?.toAst(),
        valueType = VariableValueType(proto.valueType.name),
        position = proto.position.toAst()
    )
}

fun Statements.FunctionDefinitionStatement.toAst(): FunctionDefinitionStatement {
    val proto = this

    return FunctionDefinitionStatement(
        name = proto.name,
        parameters = proto.parametersList.map { it.toAst() },
        statements = proto.statementsList.map { it.toAst() },
        returnType = VariableValueType(proto.returnType.name),
        position = proto.position.toAst()
    )
}

fun Statements.WhileDefinitionStatement.toAst(): WhileDefinitionStatement {
    val proto = this

    return WhileDefinitionStatement(
        whileCondition = proto.condition.toAst(),
        statements = proto.statementsList.map { it.toAst() },
        position = proto.position.toAst()
    )
}

fun Statements.Parameter.toAst(): Parameter {
    val proto = this

    return Parameter(
        name = proto.name,
        paramType = when (proto.type) {
            Statements.ParameterType.TYPED -> ParameterType.TYPE
            Statements.ParameterType.THIS -> ParameterType.THIS
            Statements.ParameterType.SUPER -> ParameterType.SUPER
            else -> throw UnsupportedOperationException()
        },
        valueType = VariableValueType(proto.valueType.name),
        position = proto.position.toAst()
    )
}

fun Statements.ConstructorDefinitionStatement.toAst(): ConstructorDefinitionStatement {
    val proto = this

    return ConstructorDefinitionStatement(
        className = proto.className,
        constructorName = proto.constructorName,
        parameters = proto.parametersList.map { it.toAst() },
        body = proto.bodyList.map { it.toAst() },
        thisConstructor = proto.thisConstructor?.toAst(),
        position = proto.position.toAst()
    )
}

fun Statements.ThisConstructorDefinition.toAst() : ThisConstructorDefinition {
    val proto = this

    return ThisConstructorDefinition(
        parameters = proto.parametersList.map { it.toAst() },
        position = proto.position.toAst()
    )
}

fun Statements.IfBlock.toAst(): IfBlock {
    val proto = this

    return IfBlock(
        condition = proto.condition.toAst(),
        statements = proto.statementsList.map { it.toAst() },
        blockType = proto.blockType.toAst(),
        position = proto.position.toAst()
    )

}

fun Statements.BlockType.toAst(): BlockType {
    return when (this) {
        Statements.BlockType.IF_BLOCK -> BlockType.IfBlock
        Statements.BlockType.ELSE_IF_BLOCK -> BlockType.ElseIfBlock
        Statements.BlockType.ELSE_BLOCK -> BlockType.ElseBlock
        else -> throw UnsupportedOperationException()
    }
}

