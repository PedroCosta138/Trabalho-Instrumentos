package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaInstrumentos(db: SQLiteDatabase):TabelaBD(db, NOME_TABELA) {


    override fun cria() {
        db.execSQL("CREATE TABLE $NOME_TABELA($CHAVE_TABELA,$CAMPO_NOME TEXT NOT NULL, $CAMPO_TIPO TEXT NOT NULL,$CAMPO_PAISORIGEM TEXT NOT NULL,$CAMPO_FK_BRAND INTEGER NOT NULL," +
                " FOREIGN KEY(id_brands) REFERENCES ${TabelaBrands.NOME_TABELA}(${BaseColumns._ID})ON DELETE RESTRICT)")
    }

    companion object{
        const val NOME_TABELA = "Instrumentos"
        const val CAMPO_NOME = "nome"
        const val CAMPO_TIPO= "tipo"
        const val CAMPO_PAISORIGEM = "paisOrigem"
        const val CAMPO_FK_BRAND ="id_brand"
    }


}
