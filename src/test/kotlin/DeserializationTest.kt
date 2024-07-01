import org.i3biscotti.kotlin2ast.ast.mapping.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.junit.Test
import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.ast.serialization.protocol.protobuf2kotlin.toAst
import kotlin.test.assertEquals

class DeserializationTest : ITest {
    private fun parseResource(
        resourceName: String,
    ): ProgramFile {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            return parseResult.root!!.toAst()
        } else {
            throw Exception("result was null")
        }
    }

    @Test
    override fun testVarDeclarationStatement() {
        val programFile =  parseResource("task1/varDeclarationStatement")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.VARIABLE
                            name = "name"
                            value = protocol.expression {
                                stringLit = protocol.stringLit {
                                    value = "\"Simone\""
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.IMMUTABLE
                            name = "age"
                            valueType = protocol.variableValueType { name = "int" }
                            value = protocol.expression {
                                intLit = protocol.intLit {
                                    value = "16"
                                }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.CONSTANT
                            name = "isOld"
                            valueType = protocol.variableValueType { name = "boolean" }
                            value = protocol.expression {
                                boolLit = protocol.boolLit {
                                    value = "true"
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        assignmentStatement = protocol.assignmentStatement {
                            name = "height"
                            value = protocol.expression {
                                decLit = protocol.decLit {
                                    value = "12.3"
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )

    }

    @Test
    override fun testBinaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryMathExpressionDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                binaryMathExpression = protocol.binaryMathExpression {
                                    left = protocol.expression {
                                        parenthesisExpression = protocol.parenthesisExpression {
                                            value = protocol.expression {
                                                binaryMathExpression = protocol.binaryMathExpression {
                                                    left = protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "3"
                                                        }
                                                    }
                                                    right = protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "4"
                                                        }
                                                    }
                                                    operand = protocol.Expressions.MathOperand.PLUS
                                                }
                                            }
                                        }
                                    }
                                    right = protocol.expression {
                                        parenthesisExpression = protocol.parenthesisExpression {
                                            value = protocol.expression {
                                                binaryMathExpression = protocol.binaryMathExpression {
                                                    left = protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "3"
                                                        }
                                                    }
                                                    right = protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "7"
                                                        }
                                                    }
                                                    operand = protocol.Expressions.MathOperand.MINUS
                                                }
                                            }
                                        }
                                    }
                                    operand = protocol.Expressions.MathOperand.TIMES
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testBinaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryLogicExpressionDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                binaryLogicExpression = protocol.binaryLogicExpression {
                                    left = protocol.expression {
                                        parenthesisExpression = protocol.parenthesisExpression {
                                            value = protocol.expression {
                                                binaryLogicExpression = protocol.binaryLogicExpression {
                                                    left = protocol.expression {
                                                        intLit = protocol.intLit { value = "3" }
                                                    }
                                                    right = protocol.expression {
                                                        intLit = protocol.intLit { value = "4" }
                                                    }
                                                    operand = protocol.Expressions.LogicOperand.OR
                                                }
                                            }
                                        }
                                    }
                                    right = protocol.expression {
                                        parenthesisExpression = protocol.parenthesisExpression {
                                            value = protocol.expression {
                                                binaryLogicExpression = protocol.binaryLogicExpression {
                                                    left = protocol.expression {
                                                        intLit = protocol.intLit { value = "3" }
                                                    }
                                                    right = protocol.expression {
                                                        intLit = protocol.intLit { value = "7" }
                                                    }
                                                    operand = protocol.Expressions.LogicOperand.OR
                                                }
                                            }
                                        }
                                    }
                                    operand = protocol.Expressions.LogicOperand.AND
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testUnaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryMathExpressionDefinitionStatement")


        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                parenthesisExpression = protocol.parenthesisExpression {
                                    value = protocol.expression {
                                        unaryMathExpression = protocol.unaryMathExpression {
                                            value = protocol.expression {
                                                intLit = protocol.intLit {
                                                    value = "3"
                                                }
                                            }
                                            operand = protocol.Expressions.MathOperand.MINUS
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
            }.toAst()
        )

    }

    @Test
    override fun testUnaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryLogicExpressionDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                unaryLogicExpression = protocol.unaryLogicExpression {
                                    operand = protocol.Expressions.LogicOperand.NOT
                                    value = protocol.expression {
                                        parenthesisExpression = protocol.parenthesisExpression {
                                            value = protocol.expression {
                                                varReferenceExpression = protocol.varReferenceExpression {
                                                    name = "a"
                                                }
                                            }
                                        }
                                    }
                                    operand = protocol.Expressions.LogicOperand.NOT
                                }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun testIfDefinitionStatement() {
        val programFile = parseResource("task3/ifDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        ifDefinitionStatement = protocol.ifDefinitionStatement {
                            ifBlock = protocol.ifBlock {
                                condition = protocol.expression {
                                    binaryComparisonExpression = protocol.binaryComparisonExpression {
                                        left = protocol.expression {
                                            varReferenceExpression = protocol.varReferenceExpression {
                                                name = "voto"
                                            }
                                        }
                                        right = protocol.expression {
                                            intLit = protocol.intLit {
                                                value = "18"
                                            }
                                        }
                                        operand = protocol.Expressions.ComparisonOperand.GREATER_THAN
                                    }
                                }
                                statements.add(
                                    protocol.statement {
                                        assignmentStatement = protocol.assignmentStatement {
                                            name = "exam"
                                            value = protocol.expression {
                                                stringLit = protocol.stringLit {
                                                    value = "\"passed\""
                                                }
                                            }
                                        }
                                    }
                                )
                                blockType = protocol.Statements.BlockType.IF_BLOCK
                            }
                            elseIfBlocks.add(
                                protocol.ifBlock {
                                    condition = protocol.expression {
                                        binaryComparisonExpression = protocol.binaryComparisonExpression {
                                            left = protocol.expression {
                                                varReferenceExpression = protocol.varReferenceExpression {
                                                    name = "voto"
                                                }
                                            }
                                            right = protocol.expression {
                                                intLit = protocol.intLit {
                                                    value = "18"
                                                }
                                            }
                                            operand = protocol.Expressions.ComparisonOperand.EQUAL
                                        }
                                    }
                                    statements.add(
                                        protocol.statement {
                                            assignmentStatement = protocol.assignmentStatement {
                                                name = "exam"
                                                value = protocol.expression {
                                                    stringLit = protocol.stringLit {
                                                        value = "\"passed\""
                                                    }
                                                }
                                            }
                                        }
                                    )
                                    blockType = protocol.Statements.BlockType.ELSE_IF_BLOCK
                                }
                            )
                            elseBlock = protocol.ifBlock {
                                statements.add(
                                    protocol.statement {
                                        assignmentStatement = protocol.assignmentStatement {
                                            name = "exam"
                                            value = protocol.expression {
                                                stringLit = protocol.stringLit {
                                                    value = "\"failed\""
                                                }
                                            }
                                        }
                                    }
                                )
                                blockType = protocol.Statements.BlockType.ELSE_BLOCK
                            }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testWhileDefinitionStatement() {
        val programFile = parseResource("task4/whileDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.VARIABLE
                            name = "i"
                            value = protocol.expression {
                                intLit = protocol.intLit {
                                    value = "1"
                                }
                            }
                        }
                    }
                )
                lines.add(
                    protocol.statement {
                        whileDefinitionStatement = protocol.whileDefinitionStatement {
                            condition = protocol.expression {
                                varReferenceExpression = protocol.varReferenceExpression {
                                    name = "condition"
                                }
                            }
                            statements.add(
                                protocol.statement {
                                    ifDefinitionStatement = protocol.ifDefinitionStatement {
                                        ifBlock = protocol.ifBlock {
                                            condition = protocol.expression {
                                                binaryComparisonExpression = protocol.binaryComparisonExpression {
                                                    left = protocol.expression {
                                                        varReferenceExpression = protocol.varReferenceExpression {
                                                            name = "i"
                                                        }
                                                    }
                                                    right = protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "17"
                                                        }
                                                    }
                                                    operand = protocol.Expressions.ComparisonOperand.LESS_THAN
                                                }
                                            }
                                            statements.add(
                                                protocol.statement {
                                                    assignmentStatement = protocol.assignmentStatement {
                                                        name = "i"
                                                        value = protocol.expression {
                                                            binaryMathExpression = protocol.binaryMathExpression {
                                                                left = protocol.expression {
                                                                    varReferenceExpression =
                                                                        protocol.varReferenceExpression {
                                                                            name = "i"
                                                                        }
                                                                }
                                                                right = protocol.expression {
                                                                    intLit = protocol.intLit {
                                                                        value = "1"
                                                                    }
                                                                }
                                                                operand = protocol.Expressions.MathOperand.PLUS
                                                            }
                                                        }
                                                    }
                                                }
                                            )
                                            blockType = protocol.Statements.BlockType.IF_BLOCK
                                        }
                                        elseBlock = protocol.ifBlock {
                                            statements.add(
                                                protocol.statement {
                                                    assignmentStatement = protocol.assignmentStatement {
                                                        name = "condition"
                                                        value = protocol.expression {
                                                            boolLit = protocol.boolLit {
                                                                value = "true"
                                                            }
                                                        }
                                                    }
                                                }
                                            )
                                            blockType = protocol.Statements.BlockType.ELSE_BLOCK
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun testForDefinitionStatement() {
        val programFile = parseResource("task5/forDefinitionStatement")

        assertEquals(
            programFile,
            protocol.programFile {


                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.VARIABLE
                            name = "list"
                            value = protocol.expression {
                                listLiteralExpression = protocol.listLiteralExpression {
                                    value.add(protocol.expression { intLit = protocol.intLit { value = "1" } })
                                    value.add(protocol.expression { intLit = protocol.intLit { value = "2" } })
                                }
                            }
                        }
                    })

                lines.add(protocol.statement {
                    varDeclarationStatement = protocol.variableDeclarationStatement {
                        varType = protocol.Statements.VariableType.VARIABLE
                        name = "b"
                        value = protocol.expression {
                            intLit = protocol.intLit { value = "0" }
                        }
                    }
                })

                lines.add(
                    protocol.statement {
                        forDefinitionStatement = protocol.forDefinitionStatement {
                            forCondition = protocol.forCondition {
                                forEachCondition = protocol.forEachCondition {
                                    itemDefinition = protocol.itemDefinition {
                                        varType = protocol.Statements.VariableType.VARIABLE
                                        name = "i"
                                    }
                                    expression = protocol.expression {
                                        varReferenceExpression = protocol.varReferenceExpression {
                                            name = "list"
                                        }
                                    }
                                }
                            }

                            statements.add(
                                protocol.statement {
                                    assignmentStatement = protocol.assignmentStatement {
                                        name = "b"
                                        value = protocol.expression {
                                            binaryMathExpression = protocol.binaryMathExpression {
                                                left = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "b"
                                                    }
                                                }
                                                right = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "i"
                                                    }
                                                }
                                                operand = protocol.Expressions.MathOperand.PLUS
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun testInputExpression() {
        val programFile = parseResource("task6/inputExpression")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.IMMUTABLE
                            name = "input"
                            value = protocol.expression {
                                inputExpression = protocol.inputExpression {  }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun testOutputExpression() {
        val programFile = parseResource("task6/outputExpression")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                outputExpression = protocol.outputExpression {
                                    value = protocol.expression {
                                        varReferenceExpression = protocol.varReferenceExpression {
                                            name = "input"
                                        }
                                    }
                                }
                            }
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun voidFunctionWithoutParams() {
        val programFile = parseResource("task7/voidFunctionWithoutParams")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        functionDefinitionStatement = protocol.functionDefinitionStatement {
                            name = "emptyFunction"
                            returnType = protocol.variableValueType { name = "void" }
                        }
                    }
                )
            }.toAst()
        )
    }

    @Test
    override fun intSumFunction() {
        val programFile = parseResource("task7/intSumFunction")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        functionDefinitionStatement = protocol.functionDefinitionStatement {
                            name = "sum"
                            parameters.add(
                                protocol.parameter {
                                    name = "a"
                                    type = protocol.Statements.ParameterType.TYPED
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            parameters.add(
                                protocol.parameter {
                                    name = "b"
                                    type = protocol.Statements.ParameterType.TYPED
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            returnType = protocol.variableValueType { name = "int" }
                            statements.add(
                                protocol.statement {
                                    returnStatement = protocol.returnStatement {
                                        value = protocol.expression {
                                            binaryMathExpression = protocol.binaryMathExpression {
                                                left = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "a"
                                                    }
                                                }
                                                right = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "b"
                                                    }
                                                }
                                                operand = protocol.Expressions.MathOperand.PLUS
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun callFunction() {
        val programFile = parseResource("task7/callFunction")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        functionDefinitionStatement = protocol.functionDefinitionStatement {
                            name = "operations"
                            parameters.add(
                                protocol.parameter {
                                    name = "a"
                                    type = protocol.Statements.ParameterType.TYPED
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            parameters.add(
                                protocol.parameter {
                                    name = "b"
                                    type = protocol.Statements.ParameterType.TYPED
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            parameters.add(
                                protocol.parameter {
                                    name = "c"
                                    type = protocol.Statements.ParameterType.TYPED
                                    valueType = protocol.variableValueType { name = "boolean" }
                                }
                            )
                            returnType = protocol.variableValueType { name = "boolean" }
                            statements.add(
                                protocol.statement {
                                    varDeclarationStatement = protocol.variableDeclarationStatement {
                                        varType = protocol.Statements.VariableType.VARIABLE
                                        name = "aIsGreaterThanB"
                                        value = protocol.expression {
                                            binaryComparisonExpression = protocol.binaryComparisonExpression {
                                                left = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "a"
                                                    }
                                                }
                                                right = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "b"
                                                    }
                                                }
                                                operand = protocol.Expressions.ComparisonOperand.GREATER_THAN
                                            }
                                        }
                                    }
                                }
                            )
                            statements.add(
                                protocol.statement {
                                    varDeclarationStatement = protocol.variableDeclarationStatement {
                                        varType = protocol.Statements.VariableType.IMMUTABLE
                                        name = "isGreaterAndCondition"
                                        value = protocol.expression {
                                            binaryLogicExpression = protocol.binaryLogicExpression {
                                                left = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "aIsGreaterThanB"
                                                    }
                                                }
                                                right = protocol.expression {
                                                    varReferenceExpression = protocol.varReferenceExpression {
                                                        name = "c"
                                                    }
                                                }
                                                operand = protocol.Expressions.LogicOperand.AND
                                            }
                                        }
                                    }
                                }
                            )
                            statements.add(
                                protocol.statement {
                                    returnStatement = protocol.returnStatement {
                                        value = protocol.expression {
                                            varReferenceExpression = protocol.varReferenceExpression {
                                                name = "isGreaterAndCondition"
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                )

                lines.add(
                    protocol.statement {
                        functionDefinitionStatement = protocol.functionDefinitionStatement {
                            name = "main"
                            returnType = protocol.variableValueType { name = "void" }
                            statements.add(
                                protocol.statement {
                                    varDeclarationStatement = protocol.variableDeclarationStatement {
                                        varType = protocol.Statements.VariableType.IMMUTABLE
                                        name = "result"
                                        value = protocol.expression {
                                            functionCallExpression = protocol.functionCallExpression {
                                                name = "operations"
                                                parameters.add(
                                                    protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "11"
                                                        }
                                                    }
                                                )
                                                parameters.add(
                                                    protocol.expression {
                                                        intLit = protocol.intLit {
                                                            value = "12"
                                                        }
                                                    }
                                                )
                                                parameters.add(
                                                    protocol.expression {
                                                        boolLit = protocol.boolLit {
                                                            value = "false"
                                                        }
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun emptyClass() {
        val programFile = parseResource("task8/emptyClass")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "SimpleClass"
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "SimpleClass"
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun classWithProperties() {
        val programFile = parseResource("task8/classWithProperties")

        assertEquals(
            programFile,
            protocol.programFile {
                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "SimpleClass"
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.IMMUTABLE
                                    name = "prop1"
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.IMMUTABLE
                                    name = "pro2"
                                    valueType = protocol.variableValueType { name = "boolean" }
                                }
                            )
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "SimpleClass"
                                    parameters.add(
                                        protocol.parameter {
                                            name = "prop1"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "int" }
                                        }
                                    )
                                    parameters.add(
                                        protocol.parameter {
                                            name = "pro2"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "boolean" }
                                        }
                                    )
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )

    }

    @Test
    override fun classWithMethods() {
        val programFile = parseResource("task8/classWithMethods")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "SimpleClass"
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.IMMUTABLE
                                    name = "prop1"
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.VARIABLE
                                    name = "pro2"
                                    valueType = protocol.variableValueType { name = "boolean" }
                                }
                            )
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "SimpleClass"
                                    parameters.add(
                                        protocol.parameter {
                                            name = "prop1"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "int" }
                                        }
                                    )
                                    parameters.add(
                                        protocol.parameter {
                                            name = "pro2"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "boolean" }
                                        }
                                    )
                                }
                            )
                            methods.add(
                                protocol.functionDefinitionStatement {
                                    name = "sum"
                                    parameters.add(
                                        protocol.parameter {
                                            name = "value"
                                            type = protocol.Statements.ParameterType.TYPED
                                            valueType = protocol.variableValueType { name = "int" }
                                        }
                                    )
                                    returnType = protocol.variableValueType { name = "int" }
                                    statements.add(
                                        protocol.statement {
                                            assignmentStatement = protocol.assignmentStatement {
                                                name = "pro2"
                                                value = protocol.expression {
                                                    binaryComparisonExpression = protocol.binaryComparisonExpression {
                                                        left = protocol.expression {
                                                            varReferenceExpression = protocol.varReferenceExpression {
                                                                name = "value"
                                                            }
                                                        }
                                                        right = protocol.expression {
                                                            varReferenceExpression = protocol.varReferenceExpression {
                                                                name = "prop1"
                                                            }
                                                        }
                                                        operand =
                                                            protocol.Expressions.ComparisonOperand.LESS_THAN_OR_EQUAL
                                                    }
                                                }
                                            }
                                        }
                                    )
                                    statements.add(
                                        protocol.statement {
                                            returnStatement = protocol.returnStatement {
                                                value = protocol.expression {
                                                    binaryMathExpression = protocol.binaryMathExpression {
                                                        left = protocol.expression {
                                                            varReferenceExpression = protocol.varReferenceExpression {
                                                                name = "value"
                                                            }
                                                        }
                                                        right = protocol.expression {
                                                            varReferenceExpression = protocol.varReferenceExpression {
                                                                name = "prop1"
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    )
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun classWithMultipleConstructors() {
        val programFile = parseResource("task8/classWithMultipleConstructors")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "MultiplePass"
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.IMMUTABLE
                                    name = "a"
                                    valueType = protocol.variableValueType { name = "int" }
                                }
                            )
                            properties.add(
                                protocol.variableDeclarationStatement {
                                    varType = protocol.Statements.VariableType.IMMUTABLE
                                    name = "b"
                                    valueType = protocol.variableValueType { name = "double" }
                                }
                            )
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "MultiplePass"
                                    parameters.add(
                                        protocol.parameter {
                                            name = "a"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "int" }
                                        }
                                    )
                                    parameters.add(
                                        protocol.parameter {
                                            name = "b"
                                            type = protocol.Statements.ParameterType.THIS
                                            valueType = protocol.variableValueType { name = "double" }
                                        }
                                    )
                                }
                            )
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "MultiplePass"
                                    constructorName = "s1"
                                    parameters.add(
                                        protocol.parameter {
                                            name = "a"
                                            type = protocol.Statements.ParameterType.TYPED
                                            valueType = protocol.variableValueType { name = "int" }
                                        }
                                    )
                                    thisConstructor = protocol.thisConstructorDefinition {
                                        parameters.add(
                                            protocol.expression {
                                                varReferenceExpression = protocol.varReferenceExpression {
                                                    name = "a"
                                                }
                                            }
                                        )
                                        parameters.add(
                                            protocol.expression {
                                                decLit = protocol.decLit { value = "12.1" }
                                            }
                                        )
                                    }
                                }
                            )
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun privateClass() {
        val programFile = parseResource("task8/privateClass")

        ProgramFile(
            listOf(
                ClassDefinitionStatement(
                    true,
                    "SecretWar",
                    listOf(),
                    listOf(
                        ConstructorDefinitionStatement(
                            "SecretWar",
                            "",
                            listOf(),
                            listOf(),
                            null,
                            null
                        )
                    ),
                    listOf(),
                    null,
                    null
                )
            ),
            null,
        )

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "SecretWar"
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "SecretWar"
                                }
                            )
                            encapsulation = protocol.Statements.EncapsulationType.PRIVATE
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun classHierarchy() {
        val programFile = parseResource("task8/classHierarchy")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        classDefinitionStatement = protocol.classDefinitionStatement {
                            name = "SecretWars"
                            constructors.add(
                                protocol.constructorDefinitionStatement {
                                    className = "SecretWars"
                                }
                            )
                            parentClassType = protocol.variableValueType { name = "Marvel" }
                        }
                    }
                )
             }.toAst(),
        )
    }

    @Test
    override fun objectInstance() {
        val programFile = parseResource("task9/objectInstance")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        varDeclarationStatement = protocol.variableDeclarationStatement {
                            varType = protocol.Statements.VariableType.IMMUTABLE
                            name = "element"
                            value = protocol.expression {
                                functionCallExpression = protocol.functionCallExpression {
                                    name = "ClassToInstance"
                                }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun propertyAssignment() {
        val programFile = parseResource("task9/propertyAssignment")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        objectPropertyAssignmentStatement = protocol.objectPropertyAssignmentStatement {
                            objectName = "element"
                            propertyName = "name"
                            value = protocol.expression {
                                stringLit = protocol.stringLit {
                                    value = "\"Pacco\""
                                }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }

    @Test
    override fun methodCall() {
        val programFile = parseResource("task9/methodCall")

        assertEquals(
            programFile,
            protocol.programFile {

                lines.add(
                    protocol.statement {
                        expressionDefinitionStatement = protocol.expressionDefinitionStatement {
                            value = protocol.expression {
                                objectMethodCallExpression = protocol.objectMethodCallExpression {
                                    objectName = "element"
                                    methodName = "execute"
                                }
                            }
                        }
                    }
                )
            }.toAst(),
        )
    }
}