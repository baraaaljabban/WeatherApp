package com.example.aboal.watherapp.Models

 class WeatherResult {

    //region propertys with getter and setter
     var base : String? = null

      var name: String? = null

     var visibility :Int = 0


     var  cod  : Int =0

     var  id :Int =0

     var  sys : Sys? = null

     var  dt :Int =0

     var  clouds :Clouds? =null

     var  wind :Wind? = null


     var main :Main? = null


     var  weather :ArrayList<Weather>? = null

     var  coord :Coord? = null


    //endregion

}