package com.example.instrumentos

data class Brand (
    var nome:String,
    var paisProducao:String,
    val anoFundada:Int,
    var id:Long = -1
    ){
}