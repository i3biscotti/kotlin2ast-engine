import org.i3biscotti.kotlin2ast.ast.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.transpiler.transpile
import org.junit.Test
import kotlin.test.assertEquals

class TranspileTest : ITest {
    private fun parseResource(
        resourceName: String,
    ): String {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            return parseResult.root?.toAst(false)?.transpile() ?: throw Exception("ProgramFile was null")
        } else {
            throw Exception("result was null")
        }
    }

    //region Task 1
    @Test
    override fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")

        assertEquals(
            "var name = \"Simone\"",
            programFile
        )
    }

    @Test
    override fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            "val age : Int = 16",
            programFile
        )
    }

    @Test
    override fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            "const isOld : Boolean = true",
            programFile
        )
    }

    @Test
    override fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            "height = 12.3",
            programFile
        )
    }
    //endregion

    //region Task2

    @Test
    override fun testExpressionDefinitionStatement() {
        val programFile = parseResource("task2/expressionDefinitionStatement")

        assertEquals(
            "!(True) != (((a / 3) && (b * 3)) || ((c + 3) && (d - 3)))",
            programFile
        )
    }
    @Test
    override fun testBinaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryMathExpressionDefinitionStatement")

        assertEquals(
            "(3 + 4) * (3 - 7)",
            programFile
        )
    }

    @Test
    override fun testBinaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/binaryLogicExpressionDefinitionStatement")

        assertEquals(
            "(3 || 4) && (3 || 7)",
            programFile
        )
    }

    @Test
    override fun testUnaryMathExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryMathExpressionDefinitionStatement")

        assertEquals(
            "(- 3)",
            programFile
        )
    }

    @Test
    override fun testUnaryLogicExpressionDefinitionStatement() {
        val programFile = parseResource("task2/unaryLogicExpressionDefinitionStatement")

        assertEquals(
            "!(a)",
            programFile
        )
    }
    //endregion

    //region Task 3
    @Test
    override fun testIfDefinitionStatement() {
        val programFile = parseResource("task3/ifDefinitionStatement")

        assertEquals(
            """
            |if (voto > 18) {
            |    exam = "passed"
            |} else if (voto == 18) {
            |    exam = "passed"
            |} else {
            |    exam = "failed"
            |}
            """
                .trimMargin(),
            programFile
        )
    }
    //endregion

    //region Task 4

    @Test
    override fun testWhileDefinitionStatement() {
        val programFile = parseResource("task4/whileDefinitionStatement")

        assertEquals(
            """
            |var i = 1
            |while (condition) {
            |    if (i < 17) {
            |        i = i + 1
            |    } else {
            |        condition = true
            |    }
            |}
            """
                .trimMargin(),
            programFile
        )
    }
    //endregion

    //region Task 5

    @Test
    override fun testForDefinitionStatement() {
        val programFile = parseResource("task5/forDefinitionStatement")

        assertEquals(
            """
            |var list = [1,2]
            |var b = 0
            |for (int i in list) {
            |    b = b + 1
            |}
            """
                .trimMargin(),
            programFile
        )
    }

    //region Task 7
    @Test
    override fun voidFunctionWithoutParams() {
        val programFile = parseResource("task7/voidFunctionWithoutParams")
        assertEquals(
            "fun emptyFunction() : Unit {}",
            programFile
        )
    }

    @Test
    override fun intSumFunction() {
        val programFile = parseResource("task7/intSumFunction")
        assertEquals(
            """
            |fun sum(a: Int, b: Int) : Int {
            |    return a + b
            |}
            """.trimMargin(),
            programFile
        )
    }

    @Test
    override fun callFunction() {
        val programFile = parseResource("task7/callFunction")
        assertEquals(
            """
            |fun operations(a: Int, b: Int, c: Boolean) : Boolean {
            |    var aIsGreaterThanB = a > b
            |    val isGreaterAndCondition = aIsGreaterThanB && c
            |    return isGreaterAndCondition
            |}
            |fun main() : Unit {
            |    val result = operations(11, 12, false)
            |}
            """.trimMargin(),
            programFile
        )
    }
    //endregion

    //region Task 8
    @Test
    override fun emptyClass() {
        val programFile = parseResource("task8/emptyClass")

        assertEquals(
            "class SimpleClass {}",
            programFile
        )
    }

    @Test
    override fun classWithProperties() {
        val programFile = parseResource("task8/classWithProperties")

        assertEquals(
            "class SimpleClass(val prop1 : Int, val pro2 : Boolean) {}",
            programFile
        )
    }

    @Test
    override fun classWithMethods() {
        val programFile = parseResource("task8/classWithMethods")

        assertEquals(
            """
            |class SimpleClass(val prop1 : Int, var pro2 : Boolean) {
            |    fun sum(value: Int) : Int {
            |        pro2 = value <= prop1
            |        return value + prop1
            |    }
            |}
            """.trimMargin(),
            programFile
        )
    }

    @Test
    override fun classWithMultipleConstructors() {
        val programFile = parseResource("task8/classWithMultipleConstructors")

        assertEquals(
            """
            |class MultiplePass(val a : Int, val b : Double) {
            |    constructor(a : Int) : this(a, 12.1)
            |}
            """.trimMargin(),
            programFile
        )
    }

    @Test
    override fun privateClass() {
        val programFile = parseResource("task8/privateClass")

        assertEquals(
            "private class SecretWar {}",
            programFile
        )
    }

    @Test
    override fun classHierarchy() {
        val programFile = parseResource("task8/classHierarchy")

        assertEquals(
            "class SecretWars : Marvel {}",
            programFile
        )
    }
    //endregion

    //region Task 9
    @Test
    override fun objectInstance() {
        val programFile = parseResource("task9/objectInstance")

        assertEquals(
            "val element = ClassToInstance()",
            programFile
        )
    }

    @Test
    override fun propertyAssignment() {
        val programFile = parseResource("task9/propertyAssignment")

        assertEquals(
            "element.name = \"Pacco\"",
            programFile
        )
    }

    @Test
    override fun methodCall() {
        val programFile = parseResource("task9/methodCall")

        assertEquals(
            "element.execute()",
            programFile
        )
    }
    //endregion
}

