package com.example.instrumentos

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
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

    @Test
    fun consegueLerBrands(){
        val db = getWritableDatabase()

        val brand1 = Brand("Fender", "Portugal", 1985)
        insereBrand(db,brand1)

        val brand2 = Brand("Cremona", "Itália", 1995)
        insereBrand(db,brand2)

        val tabelaBrands = TabelaBrands(db)
        val cursor = tabelaBrands.consulta(
            TabelaBrands.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(brand1.id.toString()),
            null,
            null,
            null
            )
        assert(cursor.moveToNext())

        val brandBD = Brand.fromCursor(cursor)
        assertEquals(brand1,brandBD)

        val cursorTodasBrands=tabelaBrands.consulta(
            TabelaBrands.CAMPOS,
            null,null,null,null,
            TabelaBrands.CAMPO_NOME,

        )

        assert(cursorTodasBrands.count>1)
    }

    @Test

    fun consegueLerInstrumentos(){

        val db = getWritableDatabase()
        val brand = Brand("Fender", "Portugal", 1985)
        insereBrand(db, brand)

        val instrumento1 = Instrumento("Guitarra","Cordas","Portugal",brand.id)
        insereInstrumentos(db, instrumento1)

        val instrumento2 = Instrumento("Baixo","Cordas","Estados Unidos da América",brand.id)
        insereInstrumentos(db, instrumento2)

        val tabelaInstrumentos = TabelaInstrumentos(db)
        val cursor = tabelaInstrumentos.consulta(
            TabelaInstrumentos.CAMPOS,
            "${BaseColumns._ID}=?",
            arrayOf(instrumento1.id.toString()),
            null,
            null,
            null
        )
        assert(cursor.moveToNext())

        val InstrumentoBD = Instrumento.fromCursor(cursor)
        assertEquals(instrumento1,InstrumentoBD)

        val cursorTodosInstrumentos=tabelaInstrumentos.consulta(
            TabelaInstrumentos.CAMPOS,
            null,null,null,null,
            TabelaInstrumentos.CAMPO_NOME,

            )

        assert(cursorTodosInstrumentos.count>1)


    }

}