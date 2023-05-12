package com.example.instrumentos

import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class BDInstrumentedTest {

    private fun getAppContext() = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun apagaBD(){
        getAppContext().deleteDatabase(BdInstrumentosOpenHelper.NOME_BASE_DADOS)
    }

    @Test
    fun consequeAbrirBD() {
        val db = getWritableDatabase()
        assert(db.isOpen)

    }

    private fun getWritableDatabase(): SQLiteDatabase {
        val openHelper = BdInstrumentosOpenHelper(getAppContext())
        val db = openHelper.writableDatabase
        return db
    }

    private fun insereBrand(db: SQLiteDatabase,brand: Brand) {

        brand.id= TabelaBrands(db).insere(brand.toContentValues())
        assertNotEquals(-1,brand.id)
    }
    private fun insereInstrumentos( db: SQLiteDatabase,instrumento: Instrumento) {

        instrumento.id = TabelaInstrumentos(db).insere((instrumento.toContentValues()))
        assertNotEquals(-1, instrumento.id)
    }

    @Test
    fun consegueInserirBrands(){
        val db = getWritableDatabase()

        val brand = Brand("Fender","Portugal",1985)
        val id= TabelaBrands(db).insere((brand.toContentValues()))
        TabelaBrands(db).insere(brand.toContentValues())
        assertNotEquals(-1,id)
    }

    @Test
    fun consegueInserirInstrumentos(){
        val db = getWritableDatabase()
        val brand = Brand("Fender", "Portugal", 1985)
        insereBrand(db, brand)

        val instrumento1 = Instrumento("Guitarra","Cordas","Portugal",brand.id)
        insereInstrumentos(db, instrumento1)

        val instrumento2 = Instrumento("Tambor","Percussão","Guatemala",brand.id)
        insereInstrumentos(db, instrumento2)
    }


}