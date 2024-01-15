package org.i3biscotti.kotlin2ast.ast

typealias ProcessOperationCallback = (Node) -> Unit

fun Node.process(operation: ProcessOperationCallback){
    when(this){
        is ProgramFile -> this.process(operation)
        is Statement -> this.process(operation)
        is Expression -> this.process(operation)
    }
}

fun ProgramFile.process(operation: ProcessOperationCallback){
    operation(this)

    for (line in lines){ line.process(operation) }
}

fun Statement.process(operation: ProcessOperationCallback){
    operation(this)

    when(this){
        is VarDeclarationStatement -> this.value.process(operation)
        is AssignmentStatement -> this.value.process(operation)
        is FunctionDefinitionStatement -> operation(this)
    }
}

fun Expression.process(operation: ProcessOperationCallback){
    operation(this)
}