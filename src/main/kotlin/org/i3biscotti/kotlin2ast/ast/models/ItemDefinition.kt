package org.i3biscotti.kotlin2ast.ast.models
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("ItemDefinition")
 data class ItemDefinition(
     val varType: VariableType, //Variable
     val name: String, //ID
     var valueType: VariableValueType?, // nullo
     override val position: Position?
 ): Node

