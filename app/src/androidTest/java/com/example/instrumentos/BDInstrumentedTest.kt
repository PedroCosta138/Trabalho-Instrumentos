package com.example.instrumentos

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
        val openHelper = BdInstrumentosOpenHelper(getAppContext())
        val db = openHelper.readableDatabase
        assert(db.isOpen)

    }

    @Test
    fun consegueInserirBrands(){
        val openHelper = BdInstrumentosOpenHelper(getAppContext())
        val db = openHelper.writableDatabase
        
        val brand = Brand("Fender","Portugal",1985)
        TabelaBrands(db).insere(brand.toContentValues())
    }

}