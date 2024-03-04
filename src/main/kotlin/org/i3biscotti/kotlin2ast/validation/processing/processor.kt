package org.i3biscotti.kotlin2ast.validation.processing

import org.i3biscotti.kotlin2ast.ast.models.Node
import org.i3biscotti.kotlin2ast.validation.scope.ScopeContext
import kotlin.reflect.KClass

typealias NodeProcess<T> = (T, ScopeContext) -> Unit

class NodeProcessor(val root: Node) {
    private val nodeProcesses = mutableMapOf<String, MutableList<NodeProcess<Node>>>()

    fun <T : Node> addProcess(clazz: KClass<T>, process: NodeProcess<T>) {
        val classKey = clazz.simpleName!!

        if (!nodeProcesses.containsKey(classKey)) {
            nodeProcesses[classKey] = mutableListOf()
        }

        nodeProcesses[classKey]!!.add { node, scope -> process(node as T, scope) }
    }

    fun process() {
        root.process({ node, scope ->
            val classKey = node::class.simpleName!!
            val processes = nodeProcesses[classKey]

            if (processes != null) {
                for (process in processes) {
                    process(node, scope)
                }
            }
        }, ScopeContext.rootScope)
    }
}
