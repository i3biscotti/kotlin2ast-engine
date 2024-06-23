package org.i3biscotti.kotlin2ast.ast.models



data class ForEachCondition(
    val itemDefinition: ItemDefinition,
    val value: Expression,
    override val position: Position?
) : ForCondition()
