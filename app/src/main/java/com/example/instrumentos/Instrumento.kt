package com.example.instrumentos

import android.content.ContentValues

data class Instrumento(
    var nome:String,
    var tipo:String,
    var paisOrigem:String,
    var idBrands:Long,
    var id:Long = -1) {

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaInstrumentos.CAMPO_NOME,nome)
        valores.put(TabelaInstrumentos.CAMPO_TIPO,tipo)
        valores.put(TabelaInstrumentos.CAMPO_PAISORIGEM,paisOrigem)
        return valores

    }

}