package com.example.instrumentos

data class Instrumento(
    var nome:String,
    var tipo:String,
    var paisOrigem:String,
    var idBrands:Int,
    var id:Long = -1) {
}