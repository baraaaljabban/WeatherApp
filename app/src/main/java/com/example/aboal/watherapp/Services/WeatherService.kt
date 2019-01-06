package com.example.aboal.watherapp.Services

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.aboal.watherapp.Util.WeatherAPI
import com.android.volley.VolleyError
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.http.GET
import com.android.volley.toolbox.JsonObjectRequest
import com.example.aboal.watherapp.Adapters.CityWeatherAdapter
import com.example.aboal.watherapp.MainActivity
import com.example.aboal.watherapp.Models.WeatherResult
import com.example.aboal.watherapp.R


object WeatherService {
    //  reactivex Library
    /*   fun getCityWeather(cityName : String) {
          val compositeDisposable = CompositeDisposable()
          val retrofit = RetrofitClint().getInstance()
          val cityWeatherRequest = retrofit!!.create(ICityWeather ::class.java)

          val disposable : Disposable = Single.just(cityWeatherRequest.getWeatherByCity(cityName,WeatherAPI.APP_ID,WeatherAPI.UnitType))
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
               .subscribe(object : Consumer<WeatherResult > {

                    override fun accept(t: WeatherResult) {

                   }
               }  , Consumer<Throwable> {
                   override fun accept(t: WeatherResult) {

                   }
               })


          compositeDisposable.add(disposable)
          compositeDisposable.dispose()


    }
    */

    // using volley for speeed not else
    fun getCityWeather(context : Context,cityName : String){
        val queue = Volley.newRequestQueue(context)
        val url = WeatherAPI.BaseURL+cityName+WeatherAPI.APP_ID
        //region second method
        val jsonObjReq = JsonObjectRequest(
            Request.Method.GET,
            url, null,
            Response.Listener { response ->
                val gson = Gson()

                MainActivity.weatherResult.clear()
                MainActivity.weatherResult.add(gson.fromJson<WeatherResult>(response.toString(), WeatherResult::class.java))
                MainActivity.cityWeatherAdapter = CityWeatherAdapter(MainActivity.weatherResult)
                MainActivity.cityWeatherAdapter.notifyDataSetChanged()
            }, Response.ErrorListener {
                Toast.makeText(context, R.string.didNotWrok,Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjReq)

//endregion


    }
}