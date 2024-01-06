package org.i3biscotti.kotlin2ast.validation

import org.i3biscotti.kotlin2ast.ast.*
import org.i3biscotti.kotlin2ast.parser.KotlinLangError
import org.i3biscotti.kotlin2ast.parser.specificProcess
import java.util.HashMap
import java.util.LinkedList


private fun Node.isBefore(other: Node): Boolean {

    val nodeLine = position?.start?.line!!;
    val nodeCol = position?.start?.column!!;

    val otherLine = other.position!!.start.line
    val otherCol = other.position!!.start.column

    return otherLine > nodeLine || (otherLine == nodeLine && otherCol > nodeCol)
}

fun ProgramFile.validate() : LinkedList<KotlinLangError> {
    val errors = LinkedList<KotlinLangError>()
    val variablesRegistry = HashMap<String, VarDeclarationStatement>()

    this.specificProcess(VarDeclarationStatement::class.java) {
        if (variablesRegistry.containsKey(it.name)) {
            errors.add(KotlinLangError("${it.name} is already declared", it.position?.start))
        } else {
            val implicitValueType = it.value.getType()
            val explicitValueType = it.valueType

            if (explicitValueType == null) {
                variablesRegistry[it.name] = it.copy(valueType = implicitValueType)
            } else if (implicitValueType == explicitValueType) {
                variablesRegistry[it.name] = it
            } else {
                errors.add(KotlinLangError("Expected $explicitValueType, found $implicitValueType", it.position?.start))
            }
        }
    }

    this.specificProcess(AssignmentStatement::class.java) {
        if (!variablesRegistry.containsKey(it.name)) {
            errors.add(KotlinLangError("Variable ${it.name} not declared", it.position?.start))
        } else if (it.isBefore(variablesRegistry[it.name]!!)) {
            errors.add(KotlinLangError("Variable ${it.name} not declared yet", it.position?.start))
        } else if (it.value.getType() != variablesRegistry[it.name]!!.valueType) {
            errors.add(
                KotlinLangError(
                    "Expected ${variablesRegistry[it.name]!!.valueType}, found ${it.value.getType()}",
                    it.position?.start
                )
            )
        }
    }

    return errors
}
