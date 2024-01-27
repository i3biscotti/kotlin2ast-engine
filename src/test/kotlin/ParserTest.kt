import org.antlr.v4.runtime.ParserRuleContext
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.toParseTree
import org.junit.Test
import kotlin.test.assertEquals

class ParserTest : ITest{
    private fun parseResource(
        resourceName: String,
    ): ParserRuleContext {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            if (parseResult.isCorrect()) {
                return parseResult.root ?: throw Exception("ParserRuleContext was null")
            } else {
                throw Exception(parseResult.errors.first().message)
            }
        } else {
            throw Exception("result was null")
        }
    }

    //region Task 1
    @Test
    override fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    VarDeclarationStatement
            |      T[var]
            |      T[name]
            |      T[=]
            |      StringLiteralExpression
            |        T["Simone"]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    ValDeclarationStatement
            |      T[val]
            |      T[age]
            |      T[:]
            |      IntType
            |        T[Int]
            |      T[=]
            |      IntLiteralExpression
            |        T[16]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    ConstDeclarationStatement
            |      T[const]
            |      T[isOld]
            |      T[:]
            |      BooleanType
            |        T[Boolean]
            |      T[=]
            |      BoolLiteralExpression
            |        T[true]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    AssignStatement
            |      T[height]
            |      T[=]
            |      DoubleLiteralExpression
            |        T[12.3]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    override fun testExpressionDefinitionStatement() {
        TODO("Not yet implemented")
    }

    override fun testBinaryMathExpressionDefinitionStatement() {
        TODO("Not yet implemented")
    }

    override fun testBinaryLogicExpressionDefinitionStatement() {
        TODO("Not yet implemented")
    }

    override fun testUnaryMathExpressionDefinitionStatement() {
        TODO("Not yet implemented")
    }

    override fun testUnaryLogicExpressionDefinitionStatement() {
        TODO("Not yet implemented")
    }


    //endregion

    //region Task 7
    @Test
    override fun voidFunctionWithoutParams() {
        val programFile = parseResource("task7/voidFunctionWithoutParams")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    FunctionDefinitionStatement
            |      FunctionDefinition
            |        T[fun]
            |        T[emptyFunction]
            |        T[(]
            |        T[)]
            |        Block
            |          T[{]
            |          T[}]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun intSumFunction() {
        val programFile = parseResource("task7/intSumFunction")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    FunctionDefinitionStatement
            |      FunctionDefinition
            |        T[fun]
            |        T[sum]
            |        T[(]
            |        Parameter
            |          T[a]
            |          T[:]
            |          IntType
            |            T[Int]
            |        T[,]
            |        Parameter
            |          T[b]
            |          T[:]
            |          IntType
            |            T[Int]
            |        T[)]
            |        T[:]
            |        IntType
            |          T[Int]
            |        Block
            |          T[{]
            |          ReturnStatement
            |            T[return]
            |            BinaryMathExpression
            |              VarReferenceExpression
            |                T[a]
            |              T[+]
            |              VarReferenceExpression
            |                T[b]
            |          T[}]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun callFunction() {
        val programFile = parseResource("task7/callFunction")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    FunctionDefinitionStatement
            |      FunctionDefinition
            |        T[fun]
            |        T[operations]
            |        T[(]
            |        Parameter
            |          T[a]
            |          T[:]
            |          IntType
            |            T[Int]
            |        T[,]
            |        Parameter
            |          T[b]
            |          T[:]
            |          IntType
            |            T[Int]
            |        T[,]
            |        Parameter
            |          T[c]
            |          T[:]
            |          BooleanType
            |            T[Boolean]
            |        T[)]
            |        T[:]
            |        BooleanType
            |          T[Boolean]
            |        Block
            |          T[{]
            |          VarDeclarationStatement
            |            T[var]
            |            T[aIsGreaterThanB]
            |            T[=]
            |            BinaryLogicExpression
            |              VarReferenceExpression
            |                T[a]
            |              T[>]
            |              VarReferenceExpression
            |                T[b]
            |          ValDeclarationStatement
            |            T[val]
            |            T[isGreaterAndCondition]
            |            T[=]
            |            BinaryLogicExpression
            |              VarReferenceExpression
            |                T[aIsGreaterThanB]
            |              T[&&]
            |              VarReferenceExpression
            |                T[c]
            |          ReturnStatement
            |            T[return]
            |            VarReferenceExpression
            |              T[isGreaterAndCondition]
            |          T[}]
            |  Line
            |    FunctionDefinitionStatement
            |      FunctionDefinition
            |        T[fun]
            |        T[main]
            |        T[(]
            |        T[)]
            |        Block
            |          T[{]
            |          ValDeclarationStatement
            |            T[val]
            |            T[result]
            |            T[=]
            |            FunctionCallExpression
            |              FunctionCall
            |                T[operations]
            |                T[(]
            |                IntLiteralExpression
            |                  T[11]
            |                T[,]
            |                IntLiteralExpression
            |                  T[12]
            |                T[,]
            |                BoolLiteralExpression
            |                  T[false]
            |                T[)]
            |          T[}]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )

    }
    //endregion

    //region Task 8
    @Test
    override fun emptyClass(){
        val programFile = parseResource("task8/emptyClass")
            .toParseTree()
        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[class]
                |        T[SimpleClass]
                |        T[{]
                |        T[}]
                |    T[<EOF>]
                |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun classWithProperties() {
        val programFile = parseResource("task8/classWithProperties")
            .toParseTree()

        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[class]
                |        T[SimpleClass]
                |        T[(]
                |        Parameter
                |          T[val]
                |          T[prop1]
                |          T[:]
                |          IntType
                |            T[Int]
                |        T[,]
                |        Parameter
                |          T[val]
                |          T[pro2]
                |          T[:]
                |          BooleanType
                |            T[Boolean]
                |        T[)]
                |        T[{]
                |        T[}]
                |    T[<EOF>]
                |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun classWithMethods() {
        val programFile = parseResource("task8/classWithMethods")
            .toParseTree()

        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[class]
                |        T[SimpleClass]
                |        T[(]
                |        Parameter
                |          T[val]
                |          T[prop1]
                |          T[:]
                |          IntType
                |            T[Int]
                |        T[,]
                |        Parameter
                |          T[var]
                |          T[pro2]
                |          T[:]
                |          BooleanType
                |            T[Boolean]
                |        T[)]
                |        T[{]
                |        MethodDefinitionStatement
                |          FunctionDefinition
                |            T[fun]
                |            T[sum]
                |            T[(]
                |            Parameter
                |              T[value]
                |              T[:]
                |              IntType
                |                T[Int]
                |            T[)]
                |            T[:]
                |            IntType
                |              T[Int]
                |            Block
                |              T[{]
                |              AssignStatement
                |                T[pro2]
                |                T[=]
                |                BinaryLogicExpression
                |                  VarReferenceExpression
                |                    T[value]
                |                  T[<=]
                |                  VarReferenceExpression
                |                    T[prop1]
                |              ReturnStatement
                |                T[return]
                |                BinaryMathExpression
                |                  VarReferenceExpression
                |                    T[value]
                |                  T[+]
                |                  VarReferenceExpression
                |                    T[prop1]
                |              T[}]
                |        T[}]
                |    T[<EOF>]
                |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun classWithMultipleConstructors() {
        val programFile = parseResource("task8/classWithMultipleConstructors")
            .toParseTree()

        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[class]
                |        T[MultiplePass]
                |        T[(]
                |        Parameter
                |          T[val]
                |          T[a]
                |          T[:]
                |          IntType
                |            T[Int]
                |        T[,]
                |        Parameter
                |          T[val]
                |          T[b]
                |          T[:]
                |          DoubleType
                |            T[Double]
                |        T[)]
                |        T[{]
                |        SecondaryConstructorStatement
                |          ConstructorBlock
                |            T[constructor]
                |            T[(]
                |            Parameter
                |              T[a]
                |              T[:]
                |              IntType
                |                T[Int]
                |            T[)]
                |            T[:]
                |            ThisConstructor
                |              T[this]
                |              T[(]
                |              VarReferenceExpression
                |                T[a]
                |              T[,]
                |              DoubleLiteralExpression
                |                T[12.1]
                |              T[)]
                |        T[}]
                |    T[<EOF>]
                |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun privateClass() {
        val programFile = parseResource("task8/privateClass")
            .toParseTree()

        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[private]
                |        T[class]
                |        T[SecretWar]
                |        T[{]
                |        T[}]
                |    T[<EOF>]
            """.trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    override fun classHierarchy() {
        val programFile = parseResource("task8/classHierarchy")
            .toParseTree()

        assertEquals(
            """
                |KotlinFile
                |  Line
                |    ClassDefinitionStatement
                |      ClassDefinition
                |        T[class]
                |        T[SecretWars]
                |        T[:]
                |        CustomType
                |          T[Marvel]
                |        T[{]
                |        T[}]
                |    T[<EOF>]
            """.trimMargin(),
            programFile.multiLineString()
        )
    }
    //endregion
}