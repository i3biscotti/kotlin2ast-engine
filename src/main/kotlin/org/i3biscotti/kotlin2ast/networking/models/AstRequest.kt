package org.i3biscotti.kotlin2ast.networking.models

import kotlinx.serialization.Serializable

@Serializable
data class AstRequest(val code: String)