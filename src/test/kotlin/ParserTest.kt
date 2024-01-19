import org.antlr.v4.runtime.ParserRuleContext
import org.i3biscotti.kotlin2ast.ast.models.ProgramFile
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.i3biscotti.kotlin2ast.toParseTree
import kotlin.test.Test

class ParserTest {
    private fun parseResource(
        resourceName: String,
    ): ParserRuleContext {
        val parseResult = this.javaClass
            .getResourceAsStream("/${resourceName}.txt")
            ?.let { KotlinAntlrParser.parse(it) }

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
        val programFile = parseResource("task1/varDeclarationStatement.txt")
            .toParseTree()
    }

    @Test
    fun testValDeclarationStatement(){
        val programFile = parseResource("task1/valDeclarationStatement.txt")
            .toParseTree()

    }

    @Test
    fun testConstDeclarationStatement(){
        val programFile = parseResource("task1/constDeclarationStatement.txt")
            .toParseTree()

    }

    @Test
    fun testAssignmentStatement(){
        val programFile = parseResource("task1/assignmentStatement.txt")
            .toParseTree()

    }

    //endregion

}