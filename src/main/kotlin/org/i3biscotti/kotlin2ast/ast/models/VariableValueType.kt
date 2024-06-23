package org.i3biscotti.kotlin2ast.ast.models


data class VariableValueType(val name: String) {
    companion object {
        val DYNAMIC = VariableValueType("dynamic")
        val INT = VariableValueType("int")
        val DOUBLE = VariableValueType("double")
        val STRING = VariableValueType("string")
        val BOOLEAN = VariableValueType("boolean")
        val LIST = VariableValueType("list")
        val VOID = VariableValueType("void")
    }
}