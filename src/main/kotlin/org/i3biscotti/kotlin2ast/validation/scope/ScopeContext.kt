package org.i3biscotti.kotlin2ast.validation.scope


class ScopeContext(
    val declaredVariables: MutableMap<String, VariableSign> =  mutableMapOf(),
    val declaredFunctions: MutableMap<String, FunctionSign> = mutableMapOf(),
    val declaredClasses: MutableMap<String, ClassSign> = mutableMapOf(),
    val parent: ScopeContext? = null
) {
    companion object {
        val rootScope: ScopeContext by lazy { ScopeContext() }
    }

    fun wrap(
        declaredVariables: MutableMap<String, VariableSign> = mutableMapOf(),
        declaredFunctions: MutableMap<String, FunctionSign> = mutableMapOf(),
        declaredClasses: MutableMap<String, ClassSign> = mutableMapOf(),
    ): ScopeContext = ScopeContext(declaredVariables, declaredFunctions, declaredClasses, this)

    fun unwrap(): ScopeContext? = parent

    inline fun <reified T : ScopeObject> read(name: String): T? {
        var context: ScopeContext? = this

        while (context != null) {
            val registryWhereSearch: Map<String, *> = when (T::class) {
                VariableSign::class -> context.declaredVariables
                FunctionSign::class -> context.declaredFunctions
                ClassSign::class -> context.declaredClasses
                else -> throw NotImplementedError()
            }

            val result = registryWhereSearch[name]
            if (result != null && result is T) {
                return result
            }

            context = context.parent
        }

        return null
    }
}

