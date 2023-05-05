package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

private const val NOME_TABELA = "brands"

class TabelaBrands(db: SQLiteDatabase) : TabelaBD(db, "Brands") {
    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,descricao TEXT NOT NULL)")
    }
}