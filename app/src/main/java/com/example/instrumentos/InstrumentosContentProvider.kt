package com.example.instrumentos

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.provider.BaseColumns

class InstrumentosContentProvider : ContentProvider() {

    private var bdOpenHelper : BdInstrumentosOpenHelper?=null

    override fun onCreate(): Boolean {
        bdOpenHelper= BdInstrumentosOpenHelper(context)

        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
       val db = bdOpenHelper!!.readableDatabase
        val id =uri.lastPathSegment

        val endereco = uriMatcher().match(uri)


       val tabela = when (endereco){
           URI_BRANDS, URI_BRAND_ID -> TabelaBrands(db)
           URI_INSTRUMENTOS, URI_INSTRUMENTO_ID -> TabelaInstrumentos(db)
           else -> null

       }

        var (selecao, argsSelecao) = when (endereco){
            URI_BRAND_ID, URI_INSTRUMENTO_ID -> Pair("${BaseColumns._ID}=?", arrayOf(id))
            else -> Pair(selection,selectionArgs)
        }
        //content://com.example.instrumentos/instrumentos
        //selection = "nome Piano '?%'
        //selectionArgs = { ' p' }

        return tabela?.consulta(
            projection as Array<String>,
            selecao ,
            argsSelecao as Array<String>,
            null,null,
            sortOrder)
    }


    override fun getType(uri: Uri): String? {
        val endereco = uriMatcher().match(uri)

        return when(endereco){

            URI_INSTRUMENTOS-> "vdn.android.cursor.dir/$INSTRUMENTOS"
            URI_BRANDS -> "vdn.android.cursor.dir/$BRANDS"
            URI_INSTRUMENTO_ID -> "vdn.android.cursor.item/$INSTRUMENTOS"
            URI_BRAND_ID -> "vdn.android.cursor.item/$BRANDS"
            else -> null

        }

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
            val db = bdOpenHelper!!.writableDatabase

            val endereco = uriMatcher().match(uri)

            val tabela = when (endereco){
                URI_BRANDS -> TabelaBrands(db)
                URI_INSTRUMENTOS -> TabelaInstrumentos(db)
                else -> return null

            }

            val id = tabela.insere(values!!)
            if(id == -1L){
                return null
            }

        return Uri.withAppendedPath(uri,id.toString())
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {


        val db = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco) {
            URI_BRAND_ID -> TabelaBrands(db)
            URI_INSTRUMENTO_ID -> TabelaInstrumentos(db)
            else -> return 0

        }

        val id = uri.lastPathSegment!!

        return tabela.elimina( "${BaseColumns._ID}=?", arrayOf(id))
    }


    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?):
            Int {

        val db = bdOpenHelper!!.writableDatabase

        val endereco = uriMatcher().match(uri)

        val tabela = when (endereco){
            URI_BRAND_ID -> TabelaBrands(db)
            URI_INSTRUMENTO_ID -> TabelaInstrumentos(db)
            else -> return 0

        }

        val id = uri.lastPathSegment!!

         return tabela.altera(values!!,"${BaseColumns._ID}=?", arrayOf(id))

    }

    companion object{

        private const val AUTORIDADE ="com.example.instrumentos"
        const val INSTRUMENTOS = "instrumentos"
        const val BRANDS="brands"
        private const val URI_INSTRUMENTOS=200
        private const val URI_BRAND_ID =101
        private const val URI_BRANDS=100
        private const val URI_INSTRUMENTO_ID =201

        fun uriMatcher()=UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, BRANDS, URI_BRANDS)
            addURI(AUTORIDADE, "$BRANDS/#", URI_BRAND_ID)
            addURI(AUTORIDADE, INSTRUMENTOS, URI_INSTRUMENTOS)
            addURI(AUTORIDADE, "$INSTRUMENTOS/#", URI_INSTRUMENTO_ID)
        }

        //content://com.example.instrumentos/brands -> 100
        //content://com.example.instrumentos/brands/243 -> 100
        //content://com.example.instrumentos/instrumentos -> 200
    }


}