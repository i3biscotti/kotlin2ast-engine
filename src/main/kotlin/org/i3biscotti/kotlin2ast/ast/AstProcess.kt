package org.i3biscotti.kotlin2ast.ast

typealias ProcessOperationCallback = (Node) -> Unit

fun Node.process(operation: ProcessOperationCallback){
    when(this){
        is KotlinFile -> this.process(operation)
        is Statement -> this.process(operation)
        is Expression -> this.process(operation)
    }
}

fun KotlinFile.process(operation: ProcessOperationCallback){
    operation(this)

    for (line in lines){ line.process(operation) }
}

fun Statement.process(operation: ProcessOperationCallback){
    operation(this)

    when(this){
        is VarDeclarationStatement -> this.value.process(operation)
        is Assignment -> this.value.process(operation)
    }
}

fun Expression.process(operation: ProcessOperationCallback){
    operation(this)
}