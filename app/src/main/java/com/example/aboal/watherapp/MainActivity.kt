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


    private lateinit var mLayoutManager : LinearLayoutManager

    companion object {
        var weatherResult= ArrayList<WeatherResult>()
        var cityWeatherAdapter = CityWeatherAdapter(weatherResult)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handleIntent(intent)

        initView()

    }


    fun initView (){
        mLayoutManager = LinearLayoutManager(this)
        city_recycler_view.layoutManager = mLayoutManager
        city_recycler_view.adapter = cityWeatherAdapter
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
            WeatherService.getCityWeather(this,query)
        }

    }



}
