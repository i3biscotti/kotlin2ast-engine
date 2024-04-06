import org.i3biscotti.kotlin2ast.ast.mapping.*
import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.junit.Test
import kotlin.test.assertEquals

class AstTest : ITest {
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
    override fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
                        VariableType.variable,
                        "name",
                        null,
                        StringLiteralExpression("\"Simone\"", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
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
    override fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
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
    override fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    AssignmentStatement(
                        "height",
                        DoubleLiteralExpression("12.3", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }
    //endregion

    //region Task 2
    @Test
    override fun testBinaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryMathExpressionDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    ExpressionDefinitionStatement(
                        BinaryMathExpression(MathOperand.times,
                            ParenthesisExpression(
                                BinaryMathExpression(
                                    MathOperand.plus,
                                    IntLiteralExpression("3", null),
                                    IntLiteralExpression("4", null),
                                    null
                                ),
                                null
                            ),
                            ParenthesisExpression(
                                BinaryMathExpression(
                                    MathOperand.minus,
                                    IntLiteralExpression("3", null),
                                    IntLiteralExpression("7", null),
                                    null
                                ),
                                null
                            ),
                            null
                        ),
                        null
                    )
                ),
                null),
            programFile
        )
    }

    @Test
    override fun testBinaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryLogicExpressionDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    ExpressionDefinitionStatement(
                        BinaryLogicExpression(
                            LogicOperand.and,
                            ParenthesisExpression(
                                BinaryLogicExpression(
                                    LogicOperand.or,
                                    IntLiteralExpression("3",null),
                                    IntLiteralExpression("4", null),
                                    null),
                                null),
                            ParenthesisExpression(
                                BinaryLogicExpression(
                                    LogicOperand.or,
                                    IntLiteralExpression("3", null),
                                    IntLiteralExpression("7", null),
                                    null
                                ),
                                null
                            ),
                            null
                        ),
                        null
                    )
                ),
                null),

            programFile
        )
    }

    @Test
    override fun testUnaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryMathExpressionDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    ExpressionDefinitionStatement(
                        ParenthesisExpression(
                            UnaryMathExpression(
                                null,
                                MathOperand.minus,
                                IntLiteralExpression("3", null)
                            ),
                            null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun testUnaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryLogicExpressionDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    ExpressionDefinitionStatement(
                        UnaryLogicNegationExpression(
                            null,
                            ParenthesisExpression(
                                VarReferenceExpression("a", null),
                                null
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
    //endregion

    //region Task 3
    @Test
    override fun testIfDefinitionStatement() {
        val programFile = parseResource("task3/ifDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    IfDefinitionStatement(
                        IfBlock(
                            BinaryComparisonExpression(
                                ComparisonOperand.greaterThan,
                                VarReferenceExpression("voto", null),
                                IntLiteralExpression("18", null),
                                null
                            ),
                            listOf(
                                AssignmentStatement(
                                    "exam",
                                    StringLiteralExpression(""""passed"""", null),
                                    null
                                )
                            ),
                            BlockType.IfBlock,
                            null
                        ),
                        listOf(
                            IfBlock(
                                BinaryComparisonExpression(
                                    ComparisonOperand.equal,
                                    VarReferenceExpression("voto", null),
                                    IntLiteralExpression("18", null),
                                    null
                                ),
                                listOf(
                                    AssignmentStatement(
                                        "exam",
                                        StringLiteralExpression(""""passed"""", null),
                                        null
                                    )
                                ),
                                BlockType.ElseIfBlock,
                                null
                            ),
                        ),
                        IfBlock(
                            null,
                            listOf(
                                AssignmentStatement(
                                    "exam",
                                    StringLiteralExpression(""""failed"""", null),
                                    null
                                )
                            ),
                            BlockType.ElseBlock,
                            null
                        ),
                        null,
                    )
                ),
                null,
            ), programFile
        )
    }
    //endregion

    //region Task 4
    @Test
    override fun testWhileDefinitionStatement() {
        val programFile = parseResource("task4/whileDefinitionStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
                        VariableType.variable,
                        "i",
                        null,
                        IntLiteralExpression("1", null),
                        null,
                    ),
                    WhileDefinitionStatement(
                        VarReferenceExpression("condition", null),
                        listOf(
                            IfDefinitionStatement(
                                IfBlock(
                                    BinaryComparisonExpression(
                                        ComparisonOperand.lessThan,
                                        VarReferenceExpression("i", null),
                                        IntLiteralExpression("17", null),
                                        null
                                    ),
                                    listOf(
                                        AssignmentStatement(
                                            "i",
                                            BinaryMathExpression(
                                                MathOperand.plus,
                                                VarReferenceExpression("i", null),
                                                IntLiteralExpression("1", null),
                                                null
                                            ),
                                            null
                                        )
                                    ),
                                    BlockType.IfBlock,
                                    null,
                                ),
                                listOf(),
                                IfBlock(
                                    null,
                                    listOf(
                                        AssignmentStatement(
                                            "condition",
                                            BooleanLitExpression("true", null),
                                            null
                                        )
                                    ),
                                    BlockType.ElseBlock,
                                    null
                                ),
                                null
                            )
                        ),
                        null
                    )
                ),
                null
            ), programFile
        )
    }

    //region Task 5
    @Test
    override fun testForDefinitionStatement() {
        val programFile = parseResource("task5/forDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
                        VariableType.variable,
                        "list",
                        null,
                        IntLiteralExpression("[1,2]",null),
                        null,
                    ),
                    VariableDeclarationStatement(
                        VariableType.variable,
                        "b",
                        null,
                        IntLiteralExpression("0",null),
                        null,
                    ),
                    ForDefinitionStatement(
                        ParenthesisExpression(
                            ListOfExpression(listOf(
                                IntLiteralExpression("1", null),
                                IntLiteralExpression("2", null),
                                ), null),
                            null,
                        ),
                        null
                        ),
                    AssignmentStatement(
                        "b",
                        BinaryMathExpression(
                            MathOperand.plus,
                            VarReferenceExpression("b", null),
                            IntLiteralExpression("1", null),
                            null,
                        ),
                        null,
                    ),
                ),
                null
            ),
            programFile
        )
    }


    //region Task 7
    @Test
    override fun voidFunctionWithoutParams() {
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
    override fun intSumFunction() {
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
    override fun callFunction() {
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
                            VariableDeclarationStatement(
                                VariableType.variable,
                                "aIsGreaterThanB",
                                null,
                                BinaryComparisonExpression(
                                    ComparisonOperand.greaterThan,
                                    VarReferenceExpression("a", null),
                                    VarReferenceExpression("b", null),
                                    null
                                ),
                                null,
                            ),
                            VariableDeclarationStatement(
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
                            VariableDeclarationStatement(
                                VariableType.immutable,
                                "result",
                                null,

                                FunctionCallExpression(
                                    "operations",
                                    listOf(
                                        IntLiteralExpression("11", null),
                                        IntLiteralExpression("12", null),
                                        BooleanLitExpression("false", null)
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

    //region Task 8
    @Test
    override fun emptyClass() {
        val programFile = parseResource("task8/emptyClass")

        assertEquals(
            ProgramFile(
                listOf(
                    ClassDefinitionStatement(
                        false,
                        "SimpleClass",
                        listOf(),
                        listOf(
                            ConstructorDefinitionStatement(
                                "SimpleClass",
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
                null
            ),
            programFile
        )
    }

    @Test
    override fun classWithProperties() {
        val programFile = parseResource("task8/classWithProperties")

        assertEquals(
            ProgramFile(
                listOf(
                    ClassDefinitionStatement(
                        false,
                        "SimpleClass",
                        listOf(
                            PropertyDeclaration(
                                VariableType.immutable,
                                "prop1",
                                VariableValueType.INT,
                                null,
                                null
                            ),
                            PropertyDeclaration(
                                VariableType.immutable,
                                "pro2",
                                VariableValueType.BOOLEAN,
                                null,
                                null
                            )
                        ),
                        listOf(
                            ConstructorDefinitionStatement(
                                "SimpleClass",
                                "",
                                listOf(
                                    Parameter(
                                        ParameterType.THIS,
                                        "prop1",
                                        VariableValueType.INT,
                                        null
                                    ),
                                    Parameter(
                                        ParameterType.THIS,
                                        "pro2",
                                        VariableValueType.BOOLEAN,
                                        null
                                    )
                                ),
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
                null
            ),
            programFile
        )

    }

    @Test
    override fun classWithMethods() {
        val programFile = parseResource("task8/classWithMethods")

        assertEquals(
            ProgramFile(
                listOf(
                    ClassDefinitionStatement(
                        false,
                        "SimpleClass",
                        listOf(
                            PropertyDeclaration(
                                VariableType.immutable,
                                "prop1",
                                VariableValueType.INT,
                                null,
                                null
                            ),
                            PropertyDeclaration(
                                VariableType.variable,
                                "pro2",
                                VariableValueType.BOOLEAN,
                                null,
                                null
                            )
                        ),
                        listOf(
                            ConstructorDefinitionStatement(
                                "SimpleClass",
                                "",
                                listOf(
                                    Parameter(
                                        ParameterType.THIS,
                                        "prop1",
                                        VariableValueType.INT,
                                        null
                                    ),
                                    Parameter(
                                        ParameterType.THIS,
                                        "pro2",
                                        VariableValueType.BOOLEAN,
                                        null
                                    )
                                ),
                                listOf(),
                                null,
                                null
                            )
                        ),
                        listOf(
                            FunctionDefinitionStatement(
                                "sum",
                                listOf(
                                    Parameter(
                                        ParameterType.TYPE,
                                        "value",
                                        VariableValueType.INT,
                                        null
                                    )
                                ),
                                VariableValueType.INT,
                                listOf(
                                    AssignmentStatement(
                                        "pro2",
                                        BinaryComparisonExpression(
                                            ComparisonOperand.lessThanOrEqual,
                                            VarReferenceExpression("value", null),
                                            VarReferenceExpression("prop1", null),
                                            null
                                        ),
                                        null
                                    ),
                                    ReturnStatement(
                                        BinaryMathExpression(
                                            MathOperand.plus,
                                            VarReferenceExpression("value", null),
                                            VarReferenceExpression("prop1", null),
                                            null
                                        ),
                                        null
                                    )
                                ),
                                null
                            )
                        ),
                        null,
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun classWithMultipleConstructors() {
        val programFile = parseResource("task8/classWithMultipleConstructors")

        assertEquals(
            ProgramFile(
                listOf(
                    ClassDefinitionStatement(
                        false,
                        "MultiplePass",
                        listOf(
                            PropertyDeclaration(
                                VariableType.immutable,
                                "a",
                                VariableValueType.INT,
                                null,
                                null
                            ),
                            PropertyDeclaration(
                                VariableType.immutable,
                                "b",
                                VariableValueType.DOUBLE,
                                null,
                                null
                            )
                        ),

                        listOf(
                            ConstructorDefinitionStatement(
                                "MultiplePass",
                                "",
                                listOf(
                                    Parameter(
                                        ParameterType.THIS,
                                        "a",
                                        VariableValueType.INT,
                                        null
                                    ),
                                    Parameter(
                                        ParameterType.THIS,
                                        "b",
                                        VariableValueType.DOUBLE,
                                        null
                                    )
                                ),
                                listOf(),
                                null,
                                null
                            ),
                            ConstructorDefinitionStatement(
                                "MultiplePass",
                                "s1",
                                listOf(
                                    Parameter(
                                        ParameterType.TYPE,
                                        "a",
                                        VariableValueType.INT,
                                        null
                                    ),

                                    ),
                                listOf(),
                                ThisConstructorDefinition(
                                    listOf(
                                        VarReferenceExpression("a", null),
                                        DoubleLiteralExpression("12.1", null)
                                    ),
                                    null,
                                ),
                                null
                            )
                        ),
                        listOf(),
                        null,
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun privateClass() {
        val programFile = parseResource("task8/privateClass")

        assertEquals(
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
            ),
            programFile
        )
    }

    @Test
    override fun classHierarchy() {
        val programFile = parseResource("task8/classHierarchy")

        assertEquals(
            ProgramFile(
                listOf(
                    ClassDefinitionStatement(
                        false,
                        "SecretWars",
                        listOf(),
                        listOf(
                            ConstructorDefinitionStatement(
                                "SecretWars",
                                "",
                                listOf(),
                                listOf(),
                                null,
                                null
                            )
                        ),
                        listOf(),
                        VariableValueType("Marvel"),
                        null
                    )
                ),
                null,
            ),
            programFile
        )

    }
    //endregion


    //region Task 9
    @Test
    override fun objectInstance() {
        val programFile = parseResource("task9/objectInstance")

        assertEquals(
            ProgramFile(
                listOf(
                    VariableDeclarationStatement(
                        VariableType.immutable,
                        "element",
                        null,
                        FunctionCallExpression(
                            "ClassToInstance",
                            listOf(),
                            null,
                        ),
                        null,
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun propertyAssignment() {
        val programFile = parseResource("task9/propertyAssignment")

        assertEquals(
            ProgramFile(
                listOf(
                    ObjectPropertyAssignmentStatement(
                        "element",
                        "name",
                        StringLiteralExpression("\"Pacco\"", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    override fun methodCall() {
        val programFile = parseResource("task9/methodCall")

        assertEquals(
            ProgramFile(
                listOf(
                    ExpressionDefinitionStatement(
                        ObjectMethodCallExpression(
                            "element",
                            "execute",
                            listOf(),
                            null
                        ),
                        null

                    )
                ),
                null
            ),
            programFile
        )

    }
    //endregion
}