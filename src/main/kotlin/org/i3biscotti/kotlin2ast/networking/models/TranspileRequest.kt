package org.i3biscotti.kotlin2ast.networking.models


import org.i3biscotti.kotlin2ast.ast.models.ProgramFile


data class TranspileRequest(val ast: ProgramFile)