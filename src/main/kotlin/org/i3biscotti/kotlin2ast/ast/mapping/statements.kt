package org.i3biscotti.kotlin2ast.ast.mapping
import KotlinParser.*

import org.i3biscotti.kotlin2ast.ast.models.*

fun StatementContext.toAst(considerPosition: Boolean = false): Statement {
    return when (this) {
        is VarDeclarationStatementContext -> toAst(considerPosition)
        is ValDeclarationStatementContext -> toAst(considerPosition)
        is ConstDeclarationStatementContext -> toAst(considerPosition)
        is AssignStatementContext -> toAst(considerPosition)
        is IfDefinitionStatementContext -> toAst(considerPosition)
        is WhileDefinitionStatementContext -> toAst(considerPosition)
        is ForDefinitionStatementContext -> toAst(considerPosition)
        is FunctionDefinitionStatementContext -> toAst(considerPosition)
        is ExpressionDefinitionStatementContext -> toAst(considerPosition)
        is ClassDefinitionStatementContext -> toAst(considerPosition)
        is ObjectPropertyAssignmentStatementContext -> toAst(considerPosition)
        is ReturnStatementContext -> toAst(considerPosition)
        else -> throw NotImplementedError()
    }
}


fun VarDeclarationStatementContext.toAst(considerPosition: Boolean): VariableDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VariableDeclarationStatement(
        VariableType.variable,
        name,
        valueType,
        value,
        toPosition(considerPosition)
    )
}

fun ValDeclarationStatementContext.toAst(considerPosition: Boolean): VariableDeclarationStatement {
    val name = this.ID().text
    val value = this.expression()?.toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VariableDeclarationStatement(
        VariableType.immutable,
        name,
        valueType,
        value,
        toPosition(considerPosition)
    )
}

fun ConstDeclarationStatementContext.toAst(considerPosition: Boolean): VariableDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VariableDeclarationStatement(
        VariableType.constant,
        name,
        valueType,
        value,
        toPosition(considerPosition)
    )
}

fun AssignStatementContext.toAst(considerPosition: Boolean): AssignmentStatement {

    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)

    return AssignmentStatement(
        name,
        value,
        toPosition(considerPosition)
    )
}

fun ExpressionDefinitionStatementContext.toAst(considerPosition: Boolean): ExpressionDefinitionStatement {
    val exp = expression().toAst(considerPosition)
    return ExpressionDefinitionStatement(exp, toPosition(considerPosition))
}

fun ReturnStatementContext.toAst(considerPosition: Boolean): ReturnStatement {
    val exp = expression().toAst(considerPosition)
    return ReturnStatement(exp, toPosition(considerPosition))
}

// task3
fun IfDefinitionStatementContext.toAst(considerPosition: Boolean): IfDefinitionStatement {
    val ifDefinition = this.ifDefinition()
    val ifBlock = ifDefinition.ifBlock().toAst(considerPosition)
    val elseIfBlock = ifDefinition.elseIfBlock().map { it.toAst(considerPosition) }
    val elseBlock = ifDefinition.elseBlock()?.toAst(considerPosition)

    return IfDefinitionStatement(
        ifBlock,
        elseIfBlock,
        elseBlock,
        toPosition(considerPosition)
    )
}

fun IfBlockContext.toAst(considerPosition: Boolean): IfBlock {
    val condition = this.expression().toAst(considerPosition)
    val statements = this.block().statement().map { it.toAst(considerPosition) }
    val blockType = BlockType.IfBlock

    return IfBlock(
        condition,
        statements,
        blockType,
        toPosition(considerPosition),
    )
}

fun ElseIfBlockContext.toAst(considerPosition: Boolean): IfBlock {
    val condition = this.expression()!!.toAst(considerPosition)
    val statements = this.block().statement().map { it.toAst(considerPosition) }
    val blockType = BlockType.ElseIfBlock

    return IfBlock(
        condition,
        statements,
        blockType,
        toPosition(considerPosition),
    )
}

fun ElseBlockContext.toAst(considerPosition: Boolean): IfBlock {
    val statements = this.block().statement().map { it.toAst(considerPosition) }
    val blockType = BlockType.ElseBlock

    return IfBlock(
        null,
        statements,
        blockType,
        toPosition(considerPosition),
    )
}

//task4
fun WhileDefinitionStatementContext.toAst(considerPosition: Boolean): WhileDefinitionStatement {
    val whileStatement = whileDefinition()
    val whileCondition = whileStatement.expression().toAst(considerPosition)
    val whileStatements = whileStatement.block().statement().map { it.toAst(considerPosition) }

    return WhileDefinitionStatement(
        whileCondition,
        whileStatements,
        toPosition(considerPosition)
    )
}

//task5
fun ForDefinitionStatementContext.toAst(considerPosition: Boolean): ForDefinitionStatement {
    val forStatement = forDefinition()

    val iteratorItem = forStatement.ID()
    val iterator = forStatement.iterator.toAst(considerPosition)
    val statements = forStatement.block().statement().map { it.toAst(considerPosition) }

    //TODO: da sistemare dopo check task 5 con giulia
    return ForDefinitionStatement(
        iterator,
        toPosition(considerPosition)
    )
}


fun FunctionDefinitionStatementContext.toAst(considerPosition: Boolean): FunctionDefinitionStatement {
    val fn = functionDefinition()

    val nameText = fn.name.text
    val returnType = antlr4ToAstValueType(fn.returnType) ?: VariableValueType.VOID
    val parameters = fn.parameter().map { it.toAst(considerPosition) }
    val statements = fn.block().statement().map { it.toAst(considerPosition) }
    return FunctionDefinitionStatement(nameText, parameters, returnType, statements, toPosition(considerPosition))
}

fun ClassDefinitionStatementContext.toAst(considerPosition: Boolean): ClassDefinitionStatement {
    val cls = classDefinition()
    val classStatements = cls.classStatement()
    val classParameters = cls.parameter()

    val className = cls.name.text

    var initBlockStatements: List<StatementContext> = listOf()
    val initStatementContextsFound = classStatements.filterIsInstance<InitStatementContext>()

    if (initStatementContextsFound.isNotEmpty()) {
        initBlockStatements = initStatementContextsFound[0].initBlock()!!.statement()
    }

    val secondaryConstructors = classStatements.filterIsInstance<SecondaryConstructorStatementContext>()
    val methods = classStatements
        .filterIsInstance<MethodDefinitionStatementContext>()
        .map { it.toAst(considerPosition) }


    val mainConstructorParameters = classParameters.map {
        it.toAst(considerPosition)
    }

    val mainConstructorStatements = initBlockStatements.map { it.toAst(considerPosition) }

    val mainConstructor = ConstructorDefinitionStatement(
        cls.name.text,
        "",
        mainConstructorParameters,
        mainConstructorStatements,
        null,
        toPosition(considerPosition)
    )

    var constructorCounter = 1

    val otherConstructors = secondaryConstructors.map {
        val block = it.constructorBlock()
        val parameters = block.parameter().map { it.toAst(considerPosition) }
        val statement = block.block()?.statement()?.map { it.toAst(considerPosition) } ?: listOf()

        val thisConstructor = block.thisConstructor().toAst(considerPosition)

        ConstructorDefinitionStatement(
            cls.name.text,
            "s${constructorCounter++}",
            parameters,
            statement,
            thisConstructor,
            toPosition(considerPosition)
        )
    }

    val constructors = listOf(mainConstructor) + otherConstructors

    val properties = classParameters
        .filter { it.VAL() != null || it.VAR() != null }
        .map { it.toAstPropertyDeclaration(considerPosition) }

    val isPrivate = cls.PRIVATE() != null

    val parentClassType = if (cls.parentClassType != null) {
        VariableValueType(cls.parentClassType.text)
    } else {
        null
    }

    return ClassDefinitionStatement(
        isPrivate,
        className,
        properties,
        constructors,
        methods,
        parentClassType,
        toPosition(considerPosition)
    )
}

fun ObjectPropertyAssignmentStatementContext.toAst(considerPosition: Boolean): ObjectPropertyAssignmentStatement {
    val objectProp = objectProperty()
    val value = expression().toAst(considerPosition)

    return ObjectPropertyAssignmentStatement(
        objectProp.objectName.text,
        objectProp.propertyName.text,
        value,
        toPosition(considerPosition)
    )
}

fun MethodDefinitionStatementContext.toAst(considerPosition: Boolean): FunctionDefinitionStatement {
    val fn = functionDefinition()

    val nameText = fn.name.text
    val returnType = antlr4ToAstValueType(fn.returnType) ?: VariableValueType.VOID
    val parameters = fn.parameter().map { it.toAst(considerPosition) }
    val statements = fn.block().statement().map { it.toAst(considerPosition) }
    return FunctionDefinitionStatement(nameText, parameters, returnType, statements, toPosition(considerPosition))
}

fun ThisConstructorContext.toAst(considerPosition: Boolean): ThisConstructorDefinition {
    val params = expression().map { it.toAst(considerPosition) }
    return ThisConstructorDefinition(params, toPosition(considerPosition))
}
