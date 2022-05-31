package com.wookie_soft.ex101naversearchapi.model

data class NaverApiResponceResult( val total:Int ,
                                    val display:Int,
                                    val items:MutableList<SearchItem>)


data class SearchItem(
    var title:String,
    var link :String,
    var image:String,
    var lprice:String,
    var brand:String
)
