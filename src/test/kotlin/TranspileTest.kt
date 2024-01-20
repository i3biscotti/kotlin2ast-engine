import org.i3biscotti.kotlin2ast.ast.models.*
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
            ).transpile(),
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
            ).transpile(),
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
            ).transpile(),
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
            ).transpile(),
            programFile
        )
    }

    //endregion
}