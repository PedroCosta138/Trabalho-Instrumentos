package com.example.instrumentos

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import androidx.core.content.contentValuesOf

data class Brand (
    var nome:String,
    var paisProducao:String,
    val anoFundada:Int,
    var id:Long = -1
    ){

    fun toContentValues():ContentValues{
        val valores = ContentValues()

            valores.put(TabelaBrands.CAMPO_NOME,nome)
            valores.put(TabelaBrands.CAMPO_PAISPRODUCAO,paisProducao)
            valores.put(TabelaBrands.CAMPO_ANOFUNDADA,anoFundada)
        return valores

    }

    companion object{
        fun fromCursor(cursor:Cursor):Brand{
            val posNome = cursor.getColumnIndex(TabelaBrands.CAMPO_NOME)
            val posPaisProdcao = cursor.getColumnIndex(TabelaBrands.CAMPO_PAISPRODUCAO)
            val posAnoFundada = cursor.getColumnIndex(TabelaBrands.CAMPO_ANOFUNDADA)
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val nome = cursor.getString(posNome)
            val paisProducao = cursor.getString(posPaisProdcao)
            val anoFundada = cursor.getInt(posAnoFundada)
            val id = cursor.getLong(posId)
            return Brand(nome,paisProducao,anoFundada,id)
        }
    }
}