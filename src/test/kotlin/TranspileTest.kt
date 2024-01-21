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
}