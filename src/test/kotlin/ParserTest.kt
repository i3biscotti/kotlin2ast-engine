import org.antlr.v4.runtime.ParserRuleContext
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.toParseTree
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {
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
        }else{
            throw Exception("result was null")
        }
    }

    //region Task 1

    @Test
    fun testVarDeclarationStatement(){
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
    fun testValDeclarationStatement(){
        val programFile = parseResource("task1/valDeclarationStatement")
            .toParseTree()

        assertEquals(
            """
            |KotlinFile
            |  Line
            |    VarDeclarationStatement
            |      T[val]
            |      T[age]
            |      T[=]
            |      IntLiteralExpression
            |        T[16]
            |    T[<EOF>]
            |""".trimMargin(),
            programFile.multiLineString()
        )
    }

    @Test
    fun testConstDeclarationStatement(){
        val programFile = parseResource("task1/constDeclarationStatement")
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
    fun testAssignmentStatement(){
        val programFile = parseResource("task1/assignmentStatement")
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

    //endregion

}