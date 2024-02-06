package org.i3biscotti.kotlin2ast.ast

import org.antlr.v4.runtime.*
import KotlinParser.*
import org.i3biscotti.kotlin2ast.ast.models.*


fun Token.startPoint(): Point {
    return Point(line, charPositionInLine + 1)
}

fun Token.endPoint(): Point {
    return Point(line, charPositionInLine + 1 + (text?.length ?: 0))
}

fun ParserRuleContext.toPosition(considerPosition: Boolean): Position? {
    return if (considerPosition) {
        Position(start!!.startPoint(), stop!!.endPoint())
    } else {
        null
    }
}

fun KotlinFileContext.toAst(considerPosition: Boolean = false): ProgramFile {
    val astLines = mutableListOf<Statement>()

    for (line in this.line()) {
        val statement = line.statement()
        astLines.add(statement?.toAst(considerPosition)!!)
    }

    return ProgramFile(astLines, toPosition(considerPosition))
}


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

fun antlr4ToAstValueType(type: TypeContext): VariableValueType? {
    return when (type) {
        is IntTypeContext -> VariableValueType.INT
        is DoubleTypeContext -> VariableValueType.DOUBLE
        is BooleanTypeContext -> VariableValueType.BOOLEAN
        is StringTypeContext -> VariableValueType.STRING
        is UnitTypeContext -> VariableValueType.VOID
        is CustomTypeContext -> VariableValueType(type.ID().text)
        else -> throw NotImplementedError("$type is not implemented")
    }
}

fun VarDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.variable,
        name,
        valueType,
        value,
        toPosition(considerPosition)
    )
}


fun ValDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
        VariableType.immutable,
        name,
        valueType,
        value,
        toPosition(considerPosition)
    )
}


fun ConstDeclarationStatementContext.toAst(considerPosition: Boolean): VarDeclarationStatement {
    val name = this.ID().text
    val value = this.expression().toAst(considerPosition)
    val valueType = antlr4ToAstValueType(this.type())

    return VarDeclarationStatement(
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
    val elseBlock = ifDefinition.elseBlock().toAst(considerPosition)

    return IfDefinitionStatement(
        ifBlock,
        elseIfBlock,
        elseBlock,
        toPosition(considerPosition)
    )
}

fun IfBlockContext.toAst(considerPosition: Boolean): IfBlock {
    val condition = this.expression().toAst(considerPosition)
    val statements = this.statement().map { it.toAst(considerPosition) }
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
    val statements = this.statement().map { it.toAst(considerPosition) }
    val blockType = BlockType.ElseIfBlock

    return IfBlock(
        condition,
        statements,
        blockType,
        toPosition(considerPosition),
    )
}

fun ElseBlockContext.toAst(considerPosition: Boolean): IfBlock {
    val statements = this.statement().map { it.toAst(considerPosition) }
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

fun ExpressionContext.toAst(considerPosition: Boolean): Expression {
    return when (this) {
        is BoolLiteralExpressionContext -> BooleanLitExpression(text, toPosition(considerPosition))
        is IntLiteralExpressionContext -> IntLiteralExpression(text, toPosition(considerPosition))
        is DoubleLiteralExpressionContext -> DoubleLiteralExpression(text, toPosition(considerPosition))
        is StringLiteralExpressionContext -> StringLiteralExpression(text, toPosition(considerPosition))
        is FunctionOrClassInstanceCallExpressionContext -> toAst(considerPosition)
        is BinaryMathExpressionContext -> toAst(considerPosition)
        is BinaryLogicExpressionContext -> toAst(considerPosition)
        is UnaryMathExpressionContext -> toAst(considerPosition)
        is UnaryLogicNegationExpressionContext -> toAst(considerPosition)
        is ParenthesisExpressionContext -> toAst(considerPosition)
        is VarReferenceExpressionContext -> toAst(considerPosition)
        is ListOfExpressionContext -> toAst(considerPosition)
        is ObjectMethodCallExpressionContext -> toAst(considerPosition)
        else -> throw NotImplementedError("${this.javaClass.kotlin.simpleName} not implemented")
    }
}


//task2
fun VarReferenceExpressionContext.toAst(considerPosition: Boolean): VarReferenceExpression {
    val name = this.value.text
    return VarReferenceExpression(
        name,
        toPosition(considerPosition)
    )
}

fun BinaryMathExpressionContext.toAst(considerPosition: Boolean): BinaryMathExpression {

    val left = this.left.toAst(considerPosition)
    val right = this.right.toAst(considerPosition)
    val operand: MathOperand = when (this.operand.text) {
        "+" -> MathOperand.plus
        "-" -> MathOperand.minus
        "*" -> MathOperand.times
        "/" -> MathOperand.division
        "|" -> MathOperand.module
        else -> throw NotImplementedError()
    }

    return BinaryMathExpression(
        operand,
        left,
        right,
        toPosition(considerPosition),
    )
}

fun BinaryLogicExpressionContext.toAst(considerPosition: Boolean): BinaryLogicExpression {

    val left = this.left.toAst(considerPosition)
    val right = this.right.toAst(considerPosition)
    val operand: LogicOperand = when (this.operand.text) {
        "&&" -> LogicOperand.and
        "||" -> LogicOperand.or
        "==" -> LogicOperand.equal
        "!=" -> LogicOperand.notEqual
        "<" -> LogicOperand.lessThan
        "<=" -> LogicOperand.lessThanOrEqual
        ">" -> LogicOperand.greaterThan
        ">=" -> LogicOperand.greaterThanOrEqual
        else -> throw NotImplementedError()
    }

    return BinaryLogicExpression(
        operand,
        left,
        right,
        toPosition(considerPosition),
    )
}

fun UnaryMathExpressionContext.toAst(considerPosition: Boolean): UnaryMathExpression {

    val value = this.expression().toAst(considerPosition)
    val operand: MathOperand = when (this.operand.text) {
        "+" -> MathOperand.plus
        "-" -> MathOperand.minus
        else -> throw NotImplementedError()
    }

    return UnaryMathExpression(
        toPosition(considerPosition),
        operand,
        value
    )
}

fun UnaryLogicNegationExpressionContext.toAst(considerPosition: Boolean): UnaryLogicNegationExpression {

    val value = this.expression().toAst(considerPosition)

    return UnaryLogicNegationExpression(
        toPosition(considerPosition),
        value
    )
}

fun ParenthesisExpressionContext.toAst(considerPosition: Boolean): ParenthesisExpression {

    val value = this.expression().toAst(considerPosition)

    return ParenthesisExpression(
        value,
        toPosition(considerPosition)
    )
}

fun ListOfExpressionContext.toAst(considerPosition: Boolean): ListOfExpression {
    val listStatement = listOfDefinition()
    val items = listStatement.expression().let { listOf(it) }.map { it.toAst(considerPosition) }

    return ListOfExpression(
        items,
        toPosition(considerPosition)
    )
}

fun FunctionOrClassInstanceCallExpressionContext.toAst(considerPosition: Boolean): FunctionCallExpression {
    val fn = functionOrClassInstanceCall()
    val fnName = fn.name.text
    val parameters = fn.expression().map { it.toAst(considerPosition) }

    return FunctionCallExpression(fnName, parameters, toPosition(considerPosition))
}

fun ParameterContext.toAst(considerPosition: Boolean): Parameter {
    val paramName = ID().text
    val paramValueType = antlr4ToAstValueType(type())!!
    var paramType = ParameterType.TYPE

    if (VAR() != null || VAL() != null) {
        paramType = ParameterType.THIS
    }

    return Parameter(paramType, paramName, paramValueType, toPosition(considerPosition))
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

fun ParameterContext.toAstPropertyDeclaration(considerPosition: Boolean): PropertyDeclaration {
    val name = ID().text
    val type = antlr4ToAstValueType(type())
    val propertyType = if (VAR() != null) {
        VariableType.variable
    } else if (VAL() != null) {
        VariableType.immutable
    } else {
        throw UnsupportedOperationException("Property $name not supported")
    }

    return PropertyDeclaration(
        propertyType,
        name,
        type!!,
        null,
        toPosition(considerPosition)
    )
}


fun ObjectMethodCallExpressionContext.toAst(considerPosition: Boolean): ObjectMethodCallExpression {
    val objectMethodCall = objectMethodCall()
    val method = objectMethodCall.functionOrClassInstanceCall()
    val params = method.expression().map { it.toAst(considerPosition) }

    return ObjectMethodCallExpression(
        objectMethodCall.objectName.text,
        method.name.text,
        params,
        toPosition(considerPosition)
    )
}

