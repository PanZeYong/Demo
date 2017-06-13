package com.pan.kotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 31/17",
            "Mon 6/25 - Cloudy - 31/17",
            "Mon 6/26 - Rainy - 31/17",
            "Mon 6/27 - Foggy - 31/17",
            "Mon 6/28 - TRAPPED IN WEATHER STATION - 31/17",
            "Mon 6/29 - Sunny - 31/17"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val forecastList = findViewById(R.id.recycler_view) as RecyclerView
        // 使用 Anko 库
        val forecastList : RecyclerView = find(R.id.recycler_view)
        forecastList.layoutManager = LinearLayoutManager(this)
        forecastList.adapter = ForecastListAdapter(items)
    }
}
