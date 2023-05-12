package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBrands(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,$CAMPO_NOME TEXT NOT NULL,$CAMPO_PAISPRODUCAO TEXT NOT NULL, $CAMPO_ANOFUNDADA INT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "Brands"
        const val CAMPO_NOME = "nome"
        const val CAMPO_PAISPRODUCAO = "paisProducao"
        const val CAMPO_ANOFUNDADA = "anoFundada"
    }
}