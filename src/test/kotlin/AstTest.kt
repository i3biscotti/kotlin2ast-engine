import org.i3biscotti.kotlin2ast.ast.models.*
import org.i3biscotti.kotlin2ast.ast.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.parser.KotlinParser
import org.junit.Test
import kotlin.test.assertEquals

class AstTest {
    private fun parseResource(
        resourceName: String,
    ): ProgramFile {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            return parseResult.root?.toAst(false) ?: throw Exception("ProgramFile was null")
        } else {
            throw Exception("result was null")
        }
    }

    //region Task 1
    @Test
    fun testVarDeclarationStatement() {
        val programFile = parseResource("task1/varDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.variable,
                        "name",
                        null,
                        StringLit("\"Simone\"", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testValDeclarationStatement() {
        val programFile = parseResource("task1/valDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.immutable,
                        "age",
                        VariableValueType.INT,
                        IntLit("16", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testConstDeclarationStatement() {
        val programFile = parseResource("task1/constDeclarationStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    VarDeclarationStatement(
                        VariableType.constant,
                        "isOld",
                        VariableValueType.BOOLEAN,
                        BooleanLitExpression("true", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }

    @Test
    fun testAssignmentStatement() {
        val programFile = parseResource("task1/assignmentStatement")

        assertEquals(
            ProgramFile(
                listOf(
                    AssignmentStatement(
                        "height",
                        DecLit("12.3", null),
                        null
                    )
                ),
                null
            ),
            programFile
        )
    }
    //endregion
}