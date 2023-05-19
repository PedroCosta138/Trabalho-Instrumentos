package com.example.instrumentos

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class InstrumentosContentProvider : ContentProvider() {

    private var bdOpenHelper : BdInstrumentosOpenHelper?=null

    override fun onCreate(): Boolean {
        bdOpenHelper= BdInstrumentosOpenHelper(context)

        return true
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }



    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{

        private const val AUTORIDADE ="com.example.instrumentos"
        const val INSTRUMENTOS = "instrumentos"
        const val BRANDS="brands"
        const val URI_INSTRUMENTOS=200
        const val URI_BRANDS=100

        fun uriMatcher()=UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTORIDADE, BRANDS, URI_BRANDS)
            addURI(AUTORIDADE, INSTRUMENTOS, URI_INSTRUMENTOS)
        }
    }


}