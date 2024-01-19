package org.i3biscotti.kotlin2ast.parser

import kotlinx.serialization.Serializable
import org.i3biscotti.kotlin2ast.ast.models.Point

@Serializable
data class KotlinLangError(val message: String, val position: Point?)