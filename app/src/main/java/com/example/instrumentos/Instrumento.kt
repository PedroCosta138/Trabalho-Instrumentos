package com.example.instrumentos

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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
        valores.put(TabelaInstrumentos.CAMPO_FK_BRAND,idBrands)
        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor):Instrumento{
            val posNome = cursor.getColumnIndex(TabelaInstrumentos.CAMPO_NOME)
            val posTipo = cursor.getColumnIndex(TabelaInstrumentos.CAMPO_TIPO)
            val posPaisOrigem = cursor.getColumnIndex(TabelaInstrumentos.CAMPO_PAISORIGEM)
            val posBrandFK = cursor.getColumnIndex(TabelaInstrumentos.CAMPO_FK_BRAND)
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val nome = cursor.getString(posNome)
            val tipo = cursor.getString(posTipo)
            val paisOrigem = cursor.getString(posPaisOrigem)
            val brandId = cursor.getLong(posId)
            val id = cursor.getLong(posBrandFK)
            return Instrumento(nome,tipo,paisOrigem,brandId,id)
        }
    }

}