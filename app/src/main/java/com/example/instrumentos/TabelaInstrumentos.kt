package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaInstrumentos(db: SQLiteDatabase):TabelaBD(db,"Instrumentos") {


    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,nome TEXT NOT NULL, tipo TEXT NOT NULL,paisOrigem TEXT NOT NULL,id_brands INTEGER NOT NULL)" +
                ", FOREIGN KEY(id_brands) REFERENCES ${TabelaBrands.NOME_TABELA}(${BaseColumns._ID})")
    }

    companion object{
        const val NOME_TABELA = "Instrumentos"
    }


}
