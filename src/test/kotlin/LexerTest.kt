import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class LexerTest {
    private fun lexerForCode(code: String): KotlinLexer {
        val lexer = KotlinLexer(CharStreams.fromString(code))
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
            listOf("VAR", "ID", "ASSIGN", "INT_LIT", "EOF"),
            tokens(lexerForCode("var a = 1"))
        )
    }

    @Test
    fun testVarTypedDeclarationStatement() {
        assertEquals(
            listOf("VAR", "ID", "COLONS", "DOUBLE", "ASSIGN", "DOUBLE_LIT", "EOF"),
            tokens(lexerForCode("var b : Double = 1.2"))
        )
    }

    @Test
    fun testValDeclarationStatement() {
        assertEquals(
            listOf("VAL", "ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForCode("val a = \"Hello World\""))
        )
    }

    @Test
    fun testValTypedDeclarationStatement() {
        assertEquals(
            listOf("VAL", "ID", "COLONS", "BOOLEAN", "ASSIGN", "BOOL_LIT", "EOF"),
            tokens(lexerForCode("val a : Boolean = true"))
        )
    }

    @Test
    fun testConstDeclarationStatement() {
        assertEquals(
            listOf("CONST", "ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForCode("const a = \"Hello World\""))
        )
    }

    @Test
    fun testConstTypedDeclarationStatement() {
        assertEquals(
            listOf("CONST", "ID", "COLONS", "INT", "ASSIGN", "INT_LIT", "EOF"),
            tokens(lexerForCode("const a : Int = 1"))
        )
    }

    @Test
    fun testAssignmentStatement() {
        assertEquals(
            listOf("ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForCode("s = \"Assign This\""))
        )
    }
    //endregion


}