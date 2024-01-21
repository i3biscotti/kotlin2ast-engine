import org.i3biscotti.kotlin2ast.ast.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.transpiler.transpile
import org.junit.Test
import kotlin.test.assertEquals

class TranspileTest {
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
    fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")

        assertEquals(
            "var name = \"Simone\"",
            programFile
        )
    }


    @Test
    fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            "val age : Int = 16",
            programFile
        )
    }


    @Test
    fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            "const isOld : Boolean = true",
            programFile
        )
    }


    @Test
    fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            "height = 12.3",
            programFile
        )
    }

    //endregion

    //region Task 7
    @Test
    fun voidFunctionWithoutParams() {
        val programFile = parseResource("task7/voidFunctionWithoutParams")
        assertEquals(
            "fun emptyFunction() : Unit {}",
            programFile
        )


    }

    @Test
    fun intSumFunction() {
        val programFile = parseResource("task7/intSumFunction")
        assertEquals(
            """
            |fun sum(a : Int, b : Int) : Int {
            |    return a + b
            |}
            """.trimMargin(),
            programFile
        )
    }

    @Test
    fun callFunction() {
        val programFile = parseResource("task7/callFunction")
        assertEquals(
            """
            |fun operations(a : Int, b : Int, c : Boolean) : Boolean {
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

}