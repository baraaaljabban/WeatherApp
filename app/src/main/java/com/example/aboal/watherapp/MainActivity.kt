package com.example.aboal.watherapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.aboal.watherapp.Adapters.CityWeatherAdapter
import com.example.aboal.watherapp.Models.WeatherResult
import com.example.aboal.watherapp.R.id.action_search
import com.example.aboal.watherapp.Services.WeatherService
import com.example.aboal.watherapp.Util.WeatherAPI
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleIntent(intent)


    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(action_search)

        val searchView = searchItem.actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        return true
    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            getCityWeather(this,query)
        }

    }

    fun getCityWeather(context : Context,cityName : String){
        val queue = Volley.newRequestQueue(context)
        val url = WeatherAPI.BaseURL+cityName+WeatherAPI.APP_ID
        val jsonObjReq = JsonObjectRequest(
            Request.Method.GET,
            url, null,
            Response.Listener { response ->
                val gson = Gson()
                val result = gson.fromJson<WeatherResult>(response.toString(), WeatherResult::class.java)
                inflator(result)
            }, Response.ErrorListener {
                Toast.makeText(context, R.string.didNotWrok,Toast.LENGTH_LONG).show()
            })
        queue.add(jsonObjReq)
    }

    fun inflator(result : WeatherResult){
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
    }



}
