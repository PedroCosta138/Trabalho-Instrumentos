package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBrands(db: SQLiteDatabase) : TabelaBD(db, NOME_TABELA) {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,nome TEXT NOT NULL,paisProdução TEXT NOT NULL, anoFundada INT NOT NULL)")
    }

    companion object{
        const val NOME_TABELA = "Brands"
    }
}