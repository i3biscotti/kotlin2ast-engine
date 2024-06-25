import org.antlr.v4.runtime.CharStreams
import org.junit.Test
import java.util.*
import kotlin.test.assertEquals

class LexerTest : ITest{
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
    override fun testVarDeclarationStatement() {
        assertEquals(
            listOf("VAR", "ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForResource("task1/VarDeclarationStatement"))
        )
    }

    @Test
    override fun testValDeclarationStatement() {
        assertEquals(
            listOf("VAL", "ID", "COLONS", "INT", "ASSIGN", "INT_LIT", "EOF"),
            tokens(lexerForResource("task1/ValDeclarationStatement"))
        )
    }

    @Test
    override fun testConstDeclarationStatement() {
        assertEquals(
            listOf("CONST", "ID", "COLONS", "BOOLEAN", "ASSIGN", "BOOL_LIT", "EOF"),
            tokens(lexerForResource("task1/constDeclarationStatement"))
        )
    }

    @Test
    override fun testAssignmentStatement() {
        assertEquals(
            listOf("ID", "ASSIGN", "DOUBLE_LIT", "EOF"),
            tokens(lexerForResource("task1/assignmentStatement"))
        )
    }
    //endregion

    //region Task 2
    @Test
    override fun testBinaryMathExpressionDefinitionStatement() {
        assertEquals(
            listOf("PAREN_OPEN", "INT_LIT", "PLUS", "INT_LIT", "PAREN_CLOSE", "TIMES",
                "PAREN_OPEN", "INT_LIT", "MINUS", "INT_LIT", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task2/binaryMathExpressionDefinitionStatement"))
        )
    }

    @Test
    override fun testBinaryLogicExpressionDefinitionStatement() {
        assertEquals(
            listOf("PAREN_OPEN", "INT_LIT", "OR", "INT_LIT", "PAREN_CLOSE", "AND",
                "PAREN_OPEN", "INT_LIT", "OR", "INT_LIT", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task2/binaryLogicExpressionDefinitionStatement"))
        )
    }

    @Test
    override fun testUnaryMathExpressionDefinitionStatement() {
        assertEquals(
            listOf("PAREN_OPEN", "MINUS", "INT_LIT", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task2/unaryMathExpressionDefinitionStatement"))
        )
    }

    @Test
    override fun testUnaryLogicExpressionDefinitionStatement() {
        assertEquals(
            listOf("NOT","PAREN_OPEN", "ID", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task2/unaryLogicExpressionDefinitionStatement"))
        )
    }
    //endregion

    //region Task 3
    @Test
    override fun testIfDefinitionStatement() {
        assertEquals(
            listOf("IF", "PAREN_OPEN", "ID", "GREATER_THAN", "INT_LIT",
                "PAREN_CLOSE", "GRAPH_OPEN", "NL" ,"ID", "ASSIGN", "STRING_LIT",
                "NL", "GRAPH_CLOSE", "ELSE", "IF", "PAREN_OPEN", "ID",
                "EQUAL", "INT_LIT", "PAREN_CLOSE", "GRAPH_OPEN", "NL", "ID",
                "ASSIGN", "STRING_LIT", "NL" ,"GRAPH_CLOSE", "ELSE", "GRAPH_OPEN",
                "NL", "ID", "ASSIGN", "STRING_LIT", "NL" ,"GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task3/ifDefinitionStatement"))
        )
    }
    //endregion

    //region Task 4
    @Test
    override fun testWhileDefinitionStatement() {
        assertEquals(
            listOf("VAR", "ID", "ASSIGN", "INT_LIT",
                "NL", "WHILE", "PAREN_OPEN", "ID", "PAREN_CLOSE", "GRAPH_OPEN", "NL", "IF",
                "PAREN_OPEN", "ID", "LOWER_THAN", "INT_LIT", "PAREN_CLOSE", "GRAPH_OPEN", "NL",
                "ID", "ASSIGN", "ID", "PLUS", "INT_LIT", "NL", "GRAPH_CLOSE", "ELSE", "GRAPH_OPEN",
                "NL", "ID", "ASSIGN", "BOOL_LIT", "NL", "GRAPH_CLOSE", "NL", "GRAPH_CLOSE","EOF"),
            tokens(lexerForResource("task4/whileDefinitionStatement"))
        )
    }
    //endregion

    //region Task 5
    @Test
    override fun testForDefinitionStatement() {
        assertEquals(
            listOf("VAR", "ID", "ASSIGN", "LIST_OF", "NL", "VAR", "ID", "ASSIGN", "ID", "NL", "FOR",
                   "PAREN_OPEN", "ID", "IN", "ID", "PAREN_CLOSE", "GRAPH_OPEN", "NL",
                   "VAR", "ASSIGN", "ID", "NL", "GRAPH_CLOSE","EOF"),
            tokens(lexerForResource("task5/forDefinitionStatement"))
        )
    }
    //endregion

    //region Task 5
    @Test
    override fun testInputExpression() {
        assertEquals(
            listOf("VAL", "ID", "ASSIGN", "READLINE", "PAREN_OPEN", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task6/inputExpression"))
        )
    }

    @Test
    override fun testOutputExpression() {
        assertEquals(
            listOf("PRINTLN", "PAREN_OPEN", "ID", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task6/outputExpression"))
        )
    }
    //endregion

    //region Task 7
    @Test
    override fun voidFunctionWithoutParams() {
        assertEquals(
            listOf("FUN", "ID", "PAREN_OPEN", "PAREN_CLOSE", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task7/voidFunctionWithoutParams"))
        )
    }

    @Test
    override fun intSumFunction() {
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
    override fun callFunction() {
        assertEquals(
            listOf(
                "FUN", "ID", "PAREN_OPEN",
                "ID", "COLONS", "INT", "COMMA",
                "ID", "COLONS", "INT", "COMMA",
                "ID", "COLONS", "BOOLEAN",
                "PAREN_CLOSE",
                "COLONS", "BOOLEAN",
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

    //region Task 8
    @Test
    override fun emptyClass() {
        assertEquals(
            listOf("CLASS", "ID", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task8/emptyClass"))
        )
    }

    @Test
    override fun classWithProperties() {
        assertEquals(
            listOf(
                "CLASS", "ID", "PAREN_OPEN",
                "VAL", "ID", "COLONS", "INT", "COMMA",
                "VAL", "ID", "COLONS", "BOOLEAN",
                "PAREN_CLOSE", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"
            ),
            tokens(lexerForResource("task8/classWithProperties"))
        )
    }

    @Test
    override fun classWithMethods() {
        assertEquals(
            listOf(
                "CLASS", "ID", "PAREN_OPEN",
                "VAL", "ID", "COLONS", "INT", "COMMA",
                "VAR", "ID", "COLONS", "BOOLEAN",
                "PAREN_CLOSE", "GRAPH_OPEN", "NL",

                "FUN", "ID", "PAREN_OPEN",
                "ID", "COLONS", "INT",
                "PAREN_CLOSE", "COLONS", "INT", "GRAPH_OPEN", "NL",
                "ID", "ASSIGN", "ID", "LOWER_EQUAL_THAN", "ID","NL",
                "RETURN", "ID", "PLUS", "ID", "NL",
                "GRAPH_CLOSE", "NL",

                "GRAPH_CLOSE", "EOF"
            ),
            tokens(lexerForResource("task8/classWithMethods"))
        )
    }

    @Test
    override fun classWithMultipleConstructors() {
        assertEquals(
            listOf(
                "CLASS", "ID", "PAREN_OPEN",
                "VAL", "ID", "COLONS", "INT", "COMMA",
                "VAL", "ID", "COLONS", "DOUBLE",
                "PAREN_CLOSE", "GRAPH_OPEN", "NL",

                "CONSTRUCTOR", "PAREN_OPEN",
                "ID", "COLONS", "INT",
                "PAREN_CLOSE", "COLONS", "THIS", "PAREN_OPEN",
                "ID", "COMMA",
                "DOUBLE_LIT",
                "PAREN_CLOSE", "NL",

                "GRAPH_CLOSE", "EOF"
            ),
            tokens(lexerForResource("task8/classWithMultipleConstructors"))
        )
    }

    @Test
    override fun privateClass() {
        assertEquals(
            listOf("PRIVATE", "CLASS", "ID", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task8/privateClass"))
        )
    }

    @Test
    override fun classHierarchy() {
        assertEquals(
            listOf("CLASS", "ID", "COLONS", "ID", "GRAPH_OPEN", "GRAPH_CLOSE", "EOF"),
            tokens(lexerForResource("task8/classHierarchy"))
        )
    }


    //endregion

    //region Task 9
    @Test
    override fun objectInstance() {
        assertEquals(
            listOf("VAL", "ID", "ASSIGN", "ID", "PAREN_OPEN", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task9/objectInstance"))
        )
    }

    @Test
    override fun propertyAssignment() {
        assertEquals(
            listOf("ID","DOT", "ID", "ASSIGN", "STRING_LIT", "EOF"),
            tokens(lexerForResource("task9/propertyAssignment"))
        )
    }

    @Test
    override fun methodCall() {
        assertEquals(
            listOf( "ID", "DOT", "ID", "PAREN_OPEN", "PAREN_CLOSE", "EOF"),
            tokens(lexerForResource("task9/methodCall"))
        )
    }
    //endregion
}