package org.i3biscotti.kotlin2ast.ast.serialization.protocol.kotlin2protobuf

import org.i3biscotti.kotlin2ast.ast.models.*
import protocol.Expressions

fun Expression.toProtobuf(): Expressions.Expression {
    val exp = this

    return protocol.expression {
        when (exp) {
            is IntLiteralExpression -> intLit = exp.toProtobuf()
            is DoubleLiteralExpression -> decLit = exp.toProtobuf()
            is StringLiteralExpression -> stringLit = exp.toProtobuf()
            is BooleanLitExpression -> boolLit = exp.toProtobuf()
            is FunctionCallExpression -> functionCallExpression = exp.toProtobuf()
            is BinaryLogicExpression -> binaryLogicExpression = exp.toProtobuf()
            is BinaryMathExpression -> binaryMathExpression = exp.toProtobuf()
            is ListOfExpression -> listLiteralExpression = exp.toProtobuf()
            is ObjectMethodCallExpression -> objectMethodCallExpression = exp.toProtobuf()
            is ParenthesisExpression -> parenthesisExpression = exp.toProtobuf()
            is UnaryLogicNegationExpression -> unaryLogicExpression = exp.toProtobuf()
            is UnaryMathExpression -> unaryMathExpression = exp.toProtobuf()
            is VarReferenceExpression -> varReferenceExpression = exp.toProtobuf()
            is ObjectPropertyReferenceExpression -> objectPropertyReferenceExpression = exp.toProtobuf()
            is BinaryComparisonExpression -> binaryComparisonExpression = exp.toProtobuf()
            is PostIncrementExpression -> postIncrementExpression = exp.toProtobuf()
            is PreIncrementExpression -> preIncrementExpression = exp.toProtobuf()
            is PostDecrementExpression -> postDecrementExpression = exp.toProtobuf()
            is PreDecrementExpression -> preDecrementExpression = exp.toProtobuf()
        }
    }


}

fun IntLiteralExpression.toProtobuf(): Expressions.IntLit {
    val lit = this

    return protocol.intLit {
        value = lit.value

        if (lit.position != null) {
            position = lit.position.toProtobuf()
        }
    }
}

fun DoubleLiteralExpression.toProtobuf(): Expressions.DecLit {
    val lit = this

    return protocol.decLit {
        value = lit.value

        if (lit.position != null) {
            position = lit.position.toProtobuf()
        }
    }
}

fun StringLiteralExpression.toProtobuf(): Expressions.StringLit {
    val lit = this

    return protocol.stringLit {
        value = lit.value

        if (lit.position != null) {
            position = lit.position.toProtobuf()
        }
    }
}

fun BooleanLitExpression.toProtobuf(): Expressions.BoolLit {
    val lit = this

    return protocol.boolLit {
        value = lit.value

        if (lit.position != null) {
            position = lit.position.toProtobuf()
        }
    }
}

fun FunctionCallExpression.toProtobuf(): Expressions.FunctionCallExpression {
    val call = this
    val params = call.parameters.map { it.toProtobuf() }

    return protocol.functionCallExpression {
        name = call.name
        parameters.addAll(params)

        if (call.position != null) {
            position = call.position.toProtobuf()
        }
    }
}

fun BinaryLogicExpression.toProtobuf(): Expressions.BinaryLogicExpression {
    val logic = this

    return protocol.binaryLogicExpression {
        left = logic.left.toProtobuf()
        right = logic.right.toProtobuf()

        operand = when (logic.operand) {
            LogicOperand.and -> Expressions.LogicOperand.AND
            LogicOperand.or -> Expressions.LogicOperand.OR
            LogicOperand.not -> Expressions.LogicOperand.NOT
        }

        if (logic.position != null) {
            position = logic.position.toProtobuf()
        }
    }
}

fun BinaryMathExpression.toProtobuf(): Expressions.BinaryMathExpression {
    val math = this

    return protocol.binaryMathExpression {
        left = math.left.toProtobuf()
        right = math.right.toProtobuf()

        operand = when (math.operand) {
            MathOperand.plus -> Expressions.MathOperand.PLUS
            MathOperand.minus -> Expressions.MathOperand.MINUS
            MathOperand.times -> Expressions.MathOperand.TIMES
            MathOperand.division -> Expressions.MathOperand.DIVISION
            MathOperand.module -> TODO()
        }

        if (math.position != null) {
            position = math.position.toProtobuf()
        }
    }
}

fun ListOfExpression.toProtobuf(): Expressions.ListLiteralExpression {
    val list = this
    val listItems = list.items.map { it.toProtobuf() }

    return protocol.listLiteralExpression {
        value.addAll(listItems)

        if (list.position != null) {
            position = list.position.toProtobuf()
        }
    }
}

fun ObjectMethodCallExpression.toProtobuf(): Expressions.ObjectMethodCallExpression {
    val call = this
    val params = call.params.map { it.toProtobuf() }

    return protocol.objectMethodCallExpression {
        objectName = call.objectName
        methodName = call.methodName
        parameters.addAll(params)

        if (call.position != null) {
            position = call.position.toProtobuf()
        }
    }
}

fun ParenthesisExpression.toProtobuf(): Expressions.ParenthesisExpression {
    val expr = this

    return protocol.parenthesisExpression {
        value = expr.value.toProtobuf()

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun UnaryLogicNegationExpression.toProtobuf(): Expressions.UnaryLogicExpression {
    val expr = this

    return protocol.unaryLogicExpression {
        value = expr.value.toProtobuf()
        operand = Expressions.LogicOperand.NOT

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun UnaryMathExpression.toProtobuf(): Expressions.UnaryMathExpression {
    val expr = this

    return protocol.unaryMathExpression {
        value = expr.value.toProtobuf()
        operand = when (expr.operand) {
            MathOperand.plus -> Expressions.MathOperand.PLUS
            MathOperand.minus -> Expressions.MathOperand.MINUS
            MathOperand.times -> Expressions.MathOperand.TIMES
            MathOperand.division -> Expressions.MathOperand.DIVISION
            MathOperand.module -> TODO()
        }
        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun VarReferenceExpression.toProtobuf(): Expressions.VarReferenceExpression {
    val expr = this

    return protocol.varReferenceExpression {
        name = expr.name

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun ObjectPropertyReferenceExpression.toProtobuf(): Expressions.ObjectPropertyReferenceExpression {
    val expr = this

    return protocol.objectPropertyReferenceExpression {
        objectName = expr.objectName
        propertyName = expr.propertyName

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun BinaryComparisonExpression.toProtobuf(): Expressions.BinaryComparisonExpression {
    val expr = this

    return protocol.binaryComparisonExpression {
        left = expr.left.toProtobuf()
        right = expr.right.toProtobuf()

        operand = when (expr.operand) {
            ComparisonOperand.equal -> Expressions.ComparisonOperand.EQUAL
            ComparisonOperand.notEqual -> Expressions.ComparisonOperand.NOT_EQUAL
            ComparisonOperand.greaterThan -> Expressions.ComparisonOperand.GREATER_THAN
            ComparisonOperand.greaterThanOrEqual -> Expressions.ComparisonOperand.GREATER_THAN_OR_EQUAL
            ComparisonOperand.lessThan -> Expressions.ComparisonOperand.LESS_THAN
            ComparisonOperand.lessThanOrEqual -> Expressions.ComparisonOperand.LESS_THAN_OR_EQUAL
        }

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun PostIncrementExpression.toProtobuf(): Expressions.PostIncrementExpression {
    val expr = this

    return protocol.postIncrementExpression {
        name = expr.name

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun PreIncrementExpression.toProtobuf(): Expressions.PreIncrementExpression {
    val expr = this

    return protocol.preIncrementExpression {
        name = expr.name


        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun PostDecrementExpression.toProtobuf(): Expressions.PostDecrementExpression {
    val expr = this

    return protocol.postDecrementExpression {
        name = expr.name

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}

fun PreDecrementExpression.toProtobuf(): Expressions.PreDecrementExpression {
    val expr = this

    return protocol.preDecrementExpression {
        name = expr.name

        if (expr.position != null) {
            position = expr.position.toProtobuf()
        }
    }
}