package com.example.aboal.watherapp.Services

import android.content.Context
import android.view.LayoutInflater
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
import kotlinx.android.synthetic.main.activity_main.*


object WeatherService {


    fun getCityWeather(context : Context,cityName : String){
        val queue = Volley.newRequestQueue(context)
        val url = WeatherAPI.BaseURL+cityName+WeatherAPI.APP_ID
        val jsonObjReq = JsonObjectRequest(
            Request.Method.GET,
            url, null,
            Response.Listener { response ->
                val gson = Gson()
                val view = LayoutInflater.from(context).inflate(R.layout.activity_main,null)
                val result = gson.fromJson<WeatherResult>(response.toString(), WeatherResult::class.java)
                /*
                name.text = result.name
                base.text = result.base
                cod.text = result.cod.toString()
                id.text     = result.id.toString()
                dt.text     = result.dt.toString()
                coord.text      = result.name
                visibility.text = result.visibility.toString()
                sys_type.text = result.visibility.toString()
                clouds.text = result.wind?.speed.toString()
                wind_speed.text = result.clouds?.all.toString()
                main_temp.text = result.main?.pressure.toString()
                */
            }, Response.ErrorListener {
                Toast.makeText(context, R.string.didNotWrok,Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjReq)



    }
}