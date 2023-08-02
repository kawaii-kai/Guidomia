package com.example.firstproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstproject.databinding.ActivityMainBinding
import org.json.JSONArray

import java.io.IOException
import java.io.InputStream



class MainActivity() : AppCompatActivity() {

    var arr = arrayListOf<String>()

    private lateinit var recyclerView: RecyclerView
    private var items = ArrayList<CarData>()

    private val binding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        binding.recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        read_json()
        recyclerView.adapter = CarListAdapter(list = items)

    }

    private fun addPic(title: String): Int {
        return when {
            title.contains("Land Rover Range Rover") -> {
                R.drawable.range_rover
            }

            title.contains("Roadster") -> {
                R.drawable.alpineroadster
            }

            title.contains("3300i") -> {
                R.drawable.bmw330
            }

            title.contains("Mercedes Benz") -> {
                R.drawable.mercedez_benz
            }

            else -> {
                throw Exception("Invalid Title + ${title}")
            }
        }
    }

    fun read_json() {

        var json: String? = null
        try {
            val inputStream: InputStream = assets.open("car_list.json")
            json = inputStream.bufferedReader().use { it.readText() }

            var jsonarr = JSONArray(json)

            for (i in 0..jsonarr.length() - 1) {
                var jsonobj = jsonarr.getJSONObject(i)
                val cardatatitle = "${jsonobj.getString("make")} ${jsonobj.getString("model")}"
                items.add(
                    CarData(
                        title = cardatatitle,
                        price = jsonobj.getDouble("customerPrice"),
                        pic = addPic(cardatatitle),
                    )
                )

            }



        }catch (e: IOException){

        }
    }

}