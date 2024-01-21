import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.ast.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.junit.Test
import kotlin.test.assertEquals

class AstTest {
    private fun parseResource(
        resourceName: String,
    ): ProgramFile {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            return parseResult.root?.toAst(false)
                ?: throw Exception("ProgramFile was null")
        } else {
            throw Exception("result was null")
        }
    }

    //region Task 1
    @Test
    fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.variable,
                        "name",
                        null,
                        StringLit("\"Simone\"", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.immutable,
                        "age",
                        VariableValueType.INT,
                        IntLiteralExpression("16", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.constant,
                        "isOld",
                        VariableValueType.BOOLEAN,
                        BooleanLitExpression("true", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    AssignmentStatement(
                        "height",
                        DecLit("12.3", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }


    //endregion

    //region Task 7
    @Test
    fun voidFunctionWithoutParams() {
        val programFile = parseResource("task7/voidFunctionWithoutParams")

        assertEquals(
            ProgramFile(
                listOf(
                    FunctionDefinitionStatement(
                        "emptyFunction",
                        listOf(),
                        VariableValueType.VOID,
                        listOf(),
                        null
                    )
                ),
                null
            ), programFile
        )
    }

    @Test
    fun intSumFunction() {
        val programFile = parseResource("task7/intSumFunction")

        assertEquals(
            ProgramFile(
                listOf(
                    FunctionDefinitionStatement(
                        "sum",
                        listOf(
                            Parameter(
                                ParameterType.TYPE,
                                "a",
                                VariableValueType.INT,
                                null,
                            ),
                            Parameter(
                                ParameterType.TYPE,
                                "b",
                                VariableValueType.INT,
                                null,
                            )
                        ),
                        VariableValueType.INT,
                        listOf(
                            ReturnStatement(
                                BinaryMathExpression(
                                    MathOperand.plus,
                                    VarReferenceExpression("a", null),
                                    VarReferenceExpression("b", null),
                                    null,
                                ),
                                null,
                            )
                        ),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun callFunction() {
        val programFile = parseResource("task7/callFunction")

        assertEquals(
            ProgramFile(
                listOf(
                    FunctionDefinitionStatement(
                        "operations",
                        listOf(
                            Parameter(
                                ParameterType.TYPE,
                                "a",
                                VariableValueType.INT,
                                null,
                            ),
                            Parameter(
                                ParameterType.TYPE,
                                "b",
                                VariableValueType.INT,
                                null,
                            ),
                            Parameter(
                                ParameterType.TYPE,
                                "c",
                                VariableValueType.BOOLEAN,
                                null,
                            )
                        ),
                        VariableValueType.BOOLEAN,
                        listOf(
                            VarDeclarationStatement(
                                VariableType.variable,
                                "aIsGreaterThanB",
                               null,
                                BinaryLogicExpression(
                                    LogicOperand.greaterThan,
                                    VarReferenceExpression("a", null),
                                    VarReferenceExpression("b", null),
                                    null
                                ),
                                null,
                            ),
                            VarDeclarationStatement(
                                VariableType.immutable,
                                "isGreaterAndCondition",
                                null,
                                BinaryLogicExpression(
                                    LogicOperand.and,
                                    VarReferenceExpression("aIsGreaterThanB", null),
                                    VarReferenceExpression("c", null),
                                    null
                                ),
                                null,
                            ),
                            ReturnStatement(
                                VarReferenceExpression("isGreaterAndCondition", null),
                                null,
                            )
                        ),
                        null
                    ),
                    FunctionDefinitionStatement(
                        "main",
                        listOf(),
                        VariableValueType.VOID,
                        listOf(
                            VarDeclarationStatement(
                                VariableType.immutable,
                                "result",
                                null,

                                FunctionCallExpression(
                                    "operations",
                                    listOf(
                                        IntLiteralExpression("11",null),
                                        IntLiteralExpression("12",null),
                                        BooleanLitExpression("false",null)
                                    ),
                                    null,
                                ),
                                null,
                            )
                        ),
                        null
                    )
                ),
                null,
            ),
            programFile
        )
    }
    //endregion
}