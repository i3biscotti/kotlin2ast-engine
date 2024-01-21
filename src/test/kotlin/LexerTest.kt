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
            listOf("CONST", "ID", "COLONS", "BOOLEAN", "ASSIGN", "BOOL_LIT", "EOF"),
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

    //region Task 7
    @Test
    fun voidFunctionWithoutParams() {
        assertEquals(
            listOf("FUN", "ID", "PAREN_OPEN", "PAREN_CLOSE", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task7/voidFunctionWithoutParams"))
        )
    }

    @Test
    fun intSumFunction() {
        assertEquals(
            listOf(
                "FUN",
                "ID",
                "PAREN_OPEN",
                "ID", "COLONS", "INT", "COMMA",
                "ID", "COLONS", "INT",
                "PAREN_CLOSE",
                "COLONS",
                "INT",
                "GRAPH_OPEN",
                "NL",
                "RETURN", "ID", "PLUS", "ID",
                "NL",
                "GRAPH_CLOSE",
                "EOF"
            ),
            tokens(lexerForResource("task7/intSumFunction"))
        )
    }

    @Test
    fun callFunction() {
        assertEquals(
            listOf(
                "FUN",
                "ID",
                "PAREN_OPEN",
                "ID", "COLONS", "INT", "COMMA",
                "ID", "COLONS", "INT", "COMMA",
                "ID", "COLONS", "BOOLEAN",
                "PAREN_CLOSE",
                "COLONS",
                "BOOLEAN",
                "GRAPH_OPEN",
                "NL",
                "VAR", "ID", "ASSIGN", "ID", "GREATER_THAN", "ID", "NL",
                "VAL", "ID", "ASSIGN", "ID", "AND", "ID", "NL",
                "NL",
                "RETURN", "ID",
                "NL",
                "GRAPH_CLOSE",
                "NL",
                "NL",
                "FUN",
                "ID",
                "PAREN_OPEN", "PAREN_CLOSE",
                "GRAPH_OPEN",
                "NL",
                "VAL", "ID", "ASSIGN", "ID", "PAREN_OPEN",
                "INT_LIT", "COMMA",
                "INT_LIT", "COMMA",
                "BOOL_LIT",
                "PAREN_CLOSE",
                "NL",
                "GRAPH_CLOSE",
                "EOF"
            ),
            tokens(lexerForResource("task7/callFunction"))
        )
    }
    //endregion
}