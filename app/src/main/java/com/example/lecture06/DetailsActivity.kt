package com.example.lecture06

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    private var myFavSeasonDetails: MutableList<String> = mutableListOf(
        "Silicon Valley",
        "Silicon Valley is about Richard Henricks and his company named pied piper",
        "Game of Thrones",
        "Game of Thrones is a fantasy drama",
        "Big Bang Theory",
        "Big Bang Theory is scientific sci-fi drama",
        "Prison Break",
        "Prison Break is about the story of Micheal Scofield and his brother",
        "Citizen Khan",
        "Citizen Khan: Mr. Khan , a pakistani citizen living abroad in UK",
        "Divinci Demons",
        "Divinci Demons: Story about Leonardo Divinci",
        "Mr. Robot",
        "Mr. Robot is about hacker's story and how he wants to take revenge on society",
        "House of Cards",
        "House of Cards is about underwood and his compaign to become president of USA",
        "Sherlock Holmes",
        "Sherlock Holmes: Detective to solve crimes",
        "The Witcher",
        "The witcher Geralt, a mutated monster hunter, struggles to find his place in a world in which people often prove more wicked than beasts."
    )


    private val myFavSeasonImage: MutableMap<String, Int> = mutableMapOf(
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
        setContentView(R.layout.activity_details)

        //display received season name details
        received_list_details()
        season_rating_bar.setOnRatingBarChangeListener { _, rating, _ ->
            season_rating_bar.rating = rating
        }
    }

    fun received_list_details() {
        //receive data via intent from main_activity
        val season_name_received = intent?.extras?.getString("season_item_name")
        //display received season name on text view
        display_received_season_name.text = season_name_received

        //display season details (available in myFavSeasonDetails list)
        for (i in 0..myFavSeasonDetails.size - 1) {
            if (season_name_received.equals(myFavSeasonDetails[i])) {
                displaySeasonDetails.text = myFavSeasonDetails[i + 1]
            }
        }

        //use maps to display image of selected season
        for (j in 0..myFavSeasonImage.size - 1) {
            if (season_name_received in myFavSeasonImage) {
                displaySeasonImage.setImageResource(myFavSeasonImage[season_name_received]!!)

            }
        }
    }

    //return back to "MainActivity" with some rating result
    fun returnButtonClicked(view: View) {
        val myIntent = Intent()
        myIntent.putExtra("season_rate", season_rating_bar.rating)
        setResult(Activity.RESULT_OK, myIntent)
        finish()
    }
}