package com.example.aboal.watherapp.Adapters

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.aboal.watherapp.Models.WeatherResult
import com.example.aboal.watherapp.R

class CityWeatherAdapter constructor( val weatherResult: ArrayList<WeatherResult >) : RecyclerView.Adapter<CityWeatherAdapter.ViewHolder>() {

    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        fun bindItems(data: WeatherResult) {


            val textViewName = itemView.findViewById(R.id.name) as TextView
            val textViewBase = itemView.findViewById(R.id.base) as TextView
            val textViewCode = itemView.findViewById(R.id.cod) as TextView
            val textViewId = itemView.findViewById(R.id.id) as TextView
            val textViewDt = itemView.findViewById(R.id.dt) as TextView
            val textViewCoord = itemView.findViewById(R.id.coord) as TextView
            val textViewVisibility = itemView.findViewById(R.id.visibility) as TextView
            val textViewSysType = itemView.findViewById(R.id.sys_type) as TextView
            val textViewClouds = itemView.findViewById(R.id.clouds) as TextView
            val textViewWindSpeed = itemView.findViewById(R.id.wind_speed) as TextView
            val textViewMainTemp = itemView.findViewById(R.id.main_temp) as TextView

            textViewName.text = data.name
            textViewBase.text = data.base
            textViewCode.text = data.cod.toString()
            textViewId.text     = data.id.toString()
            textViewDt.text     = data.dt.toString()
            textViewCoord.text      = data.name
            textViewVisibility.text = data.visibility.toString()
            textViewSysType.text = data.visibility.toString()
            textViewWindSpeed.text = data.wind?.speed.toString()
            textViewClouds.text = data.clouds?.all.toString()
            textViewMainTemp.text = data.main?.pressure.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_city_weather, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(weatherResult[position])
    }


    override fun getItemCount(): Int {
        return weatherResult.size
    }


}