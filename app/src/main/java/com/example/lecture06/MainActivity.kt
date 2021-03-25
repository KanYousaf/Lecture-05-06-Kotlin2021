package com.example.lecture06

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlin.system.exitProcess

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE: Int = 123
    private val my_fav_season_list: MutableList<String> = ArrayList()
    private var season_list_adapter: ArrayAdapter<String>? = null

    private val my_fav_season_image: MutableMap<String, Int> = mutableMapOf(
        "Silicon Valley" to R.drawable.siliconvalley,
        "Game of Thrones" to R.drawable.gameofthrones,
        "Big Bang Theory" to R.drawable.bigbangtheory,
        "Prison Break" to R.drawable.prisonbreak,
        "Citizen Khan" to R.drawable.citizenkhan,
        "Divinci Demons" to R.drawable.divincidemons,
        "Mr. Robot" to R.drawable.mrrobot,
        "House of Cards" to R.drawable.houseofcards,
        "Sherlock Holmes" to R.drawable.sherlockholmes,
        "The Witcher" to R.drawable.witcher
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load season names so that it can be used for listview
        loadData()
    }


    // menu options to create
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var optionSelected: String = ""
        val myIntent: Intent
        when (item.itemId) {
            R.id.home -> {
                optionSelected = "home"
                myIntent = Intent(this@MainActivity, MainActivity::class.java)
                startActivity(myIntent)
            }
            R.id.details -> {
                optionSelected = "details"
                myIntent = Intent(this@MainActivity, DetailsActivity::class.java)
                startActivity(myIntent)
            }
            R.id.settings ->
                optionSelected = "settings"
            R.id.exit ->
                exitProcess(0)
        }
        Toast.makeText(
            this@MainActivity,
            "You chose : ${optionSelected} option",
            Toast.LENGTH_SHORT
        ).show()
        return super.onOptionsItemSelected(item)
    }


    fun loadData() {
        //Add seasons' names into array list
        my_fav_season_list.add("Silicon Valley")
        my_fav_season_list.add("Game of Thrones")
        my_fav_season_list.add("Big Bang Theory")
        my_fav_season_list.add("Prison Break")
        my_fav_season_list.add("Citizen Khan")
        my_fav_season_list.add("Divinci Demons")
        my_fav_season_list.add("Mr. Robot")
        my_fav_season_list.add("House of Cards")
        my_fav_season_list.add("Sherlock Holmes")
        my_fav_season_list.add("The Witcher")
    }

    //return from "DetailsActivity.kt" to display rating value on Toast
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val received_season_rating: Float
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            received_season_rating = data!!.getFloatExtra("season_rate", 1.0f)
            Toast.makeText(this, "$received_season_rating", Toast.LENGTH_LONG).show()
        }
    }

    fun predefined_button_clicked(view: View) {
        season_list_adapter =
            ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, my_fav_season_list)
        season_list.adapter = season_list_adapter
        season_list_click_listener()
    }

    fun custom_button_clicked(view: View) {
        season_list_adapter = myCustomAdapter(this@MainActivity, my_fav_season_list, my_fav_season_image)
        season_list.adapter = season_list_adapter
        season_list_click_listener()
    }


    fun season_list_click_listener(){
        season_list.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val clickedItemName = parent?.getItemAtPosition(position).toString()
                //moving to "DetailsActivity" and return back to this activity to display retrieved results from "DetailsActivity".
                val details_intent = Intent(this@MainActivity, DetailsActivity::class.java)
                details_intent.putExtra("season_item_name", clickedItemName)
                startActivityForResult(details_intent, REQUEST_CODE)
            }
        }

        season_list.onItemLongClickListener = object : AdapterView.OnItemLongClickListener{
            override fun onItemLongClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ): Boolean {
                my_fav_season_list.removeAt(position)
                season_list_adapter!!.notifyDataSetChanged()
                return true
            }
        }
    }
}