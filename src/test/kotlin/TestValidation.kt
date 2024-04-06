import org.i3biscotti.kotlin2ast.ast.mapping.toAst
import org.i3biscotti.kotlin2ast.parser.KotlinAntlrParser
import org.i3biscotti.kotlin2ast.validation.ProgramValidator
import org.i3biscotti.kotlin2ast.validation.models.*
import org.junit.Test
import kotlin.test.assertEquals

class TestValidation {
    private fun parseResource(
        resourceName: String,
    ): ProgramValidator {
        val resource = this.javaClass.getResourceAsStream("/${resourceName}.txt")
        val parseResult = resource?.let { KotlinAntlrParser.parse(it) }

        if (parseResult != null) {
            val root = parseResult.root?.toAst(true)
                ?: throw Exception("ProgramFile was null")

            return ProgramValidator(root)
        } else {
            throw Exception("result was null")
        }
    }

    @Test
    fun variablesErrors() {
        val programFile = parseResource("validation/variable_errors")
        val errors = programFile.startValidation().map { it.javaClass.simpleName }

        assertEquals(
            listOf(
                VarNotDeclaredError::class.simpleName!!,
                VarImmutableError::class.simpleName!!,
                VarAlreadyDeclaredError::class.simpleName!!,
                VarTypeMismatchError::class.simpleName!!,
                VarValueNotAssigned::class.simpleName!!,
            ),
            errors
        )

    }

    @Test
    fun controlStatementsErrors() {
        val programFile = parseResource("validation/control_statement_errors")
        val errors = programFile.startValidation().map { it.javaClass.simpleName }

        assertEquals(
            listOf(ExpressionMismatchError::class.simpleName!!),
            errors,
        )
    }

    @Test
    fun missingMainFunction() {
        val programFile = parseResource("validation/missing_main_function")
        val errors = programFile.startValidation().map { it.javaClass.simpleName }

        assertEquals(
            listOf(MissingMainFunctionError::class.simpleName!!),
            errors
        )
    }

    fun testForDefinitionStatement() {
        TODO("Not yet implemented")
    }

    @Test
    fun functionErrors() {
        val programFile = parseResource("validation/function_errors")
        val errors = programFile.startValidation()
        val errorNames = errors.map { it.javaClass.simpleName }

        assertEquals(
            listOf(
                FunctionMissingReturnError::class.simpleName!!,
                FunctionReturnNotAllowedError::class.simpleName!!,
                FunctionSignMismatchError::class.simpleName!!,
                FunctionNotDefinedError::class.simpleName!!,
            ),
            errorNames
        )
    }

    @Test
    fun classErrors() {
        val programFile = parseResource("validation/class_errors")
        val errors = programFile.startValidation().map { it.javaClass.simpleName }

        assertEquals(
            listOf(
                ClassPropertyNotDefinedError::class.simpleName!!,
                ClassMethodNotDefinedError::class.simpleName!!,
                FunctionNotDefinedError::class.simpleName!!,
            ),
            errors
        )
    }
}