package com.example.instrumentos

import android.content.ContentValues
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

}