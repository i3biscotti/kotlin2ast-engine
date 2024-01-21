package org.i3biscotti.kotlin2ast

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode
import java.util.*

abstract class ParseTreeElement {
    abstract fun multiLineString(indentation: String = ""): String
}

class ParseTreeLeaf(val text: String) : ParseTreeElement() {
    override fun toString(): String {
        return "T[$text]"
    }

    override fun multiLineString(indentation: String): String = "${indentation}T[$text]\n"
}

class ParseTreeNode(val name: String) : ParseTreeElement() {
    val children = LinkedList<ParseTreeElement>()
    fun child(c: ParseTreeElement): ParseTreeNode {
        children.add(c)
        return this
    }

    override fun toString(): String {
        return "Node($name) $children"
    }

    override fun multiLineString(indentation: String): String {
        val sb = StringBuilder()
        sb.append("${indentation}$name\n")
        children.forEach { c -> sb.append(c.multiLineString(indentation + "  ")) }
        return sb.toString()
    }
}

fun ParserRuleContext.toParseTree(): ParseTreeNode {
    val res = ParseTreeNode(javaClass.simpleName.removeSuffix("Context"))
    children.forEach { c ->
        when (c) {
            is ParserRuleContext -> res.child(c.toParseTree())
            is TerminalNode -> if (!c.text.contains("\r") && !c.text.contains("\n")) {
                res.child(ParseTreeLeaf(c.text))
            }
        }
    }

    return res
}
