package com.example.instrumentos

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val NOME_BASE_DADOS = "Instrumentos.db"
private const val VERSAO_BASE_DADOS = 1

class BdInstrumentosOpenHelper (
    context: Context?,
) : SQLiteOpenHelper(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS) {



    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)
    TabelaBrands(db!!).cria()
    TabelaInstrumentos(db!!).cria()

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}