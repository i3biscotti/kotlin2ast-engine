package org.i3biscotti.kotlin2ast.ast.models





 data class ItemDefinition(
     val varType: VariableType, //Variable
     val name: String, //ID
     var valueType: VariableValueType?, // nullo
     override val position: Position?
 ): Node

