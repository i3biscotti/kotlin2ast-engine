import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class LexerTest {
    private fun lexerForResource(resourceName: String): KotlinLexer {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val lexer = KotlinLexer(CharStreams.fromStream(resource))
        return lexer
    }

    private fun tokens(lexer: KotlinLexer): List<String> {
        val tokens = LinkedList<String>()
        do {
            val t = lexer.nextToken()
            when (t.type) {
                -1 -> tokens.add("EOF")
                else -> if (t.type != KotlinLexer.WS) tokens.add(lexer.ruleNames[t.type - 1])
            }
        } while (t.type != -1)
        return tokens
    }


    //region Task 1
    @Test
    fun testVarDeclarationStatement() {
        assertEquals(
            listOf("VAR", "ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForResource("task1/VarDeclarationStatement"))
        )
    }

    @Test
    fun testValDeclarationStatement() {
        assertEquals(
            listOf("VAL", "ID", "COLONS", "INT", "ASSIGN", "INT_LIT", "EOF"),
            tokens(lexerForResource("task1/ValDeclarationStatement"))
        )
    }

    @Test
    fun testConstDeclarationStatement() {
        assertEquals(
            listOf("CONST", "ID","COLONS", "BOOLEAN", "ASSIGN", "BOOL_LIT", "EOF"),
            tokens(lexerForResource("task1/constDeclarationStatement"))
        )
    }

    @Test
    fun testAssignmentStatement() {
        assertEquals(
            listOf("ID", "ASSIGN", "DOUBLE_LIT", "EOF"),
            tokens(lexerForResource("task1/assignmentStatement"))
        )
    }
    //endregion


}