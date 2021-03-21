package com.example.seihekijinrou.Preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.MeetingandVotingandResult.CenterofMeetingandVoting
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.ActivityOpenjinrou2Binding

class openjinrou2 : AppCompatActivity() {
    private lateinit var binding: ActivityOpenjinrou2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOpenjinrou2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var jinrou = pref.getString("jinrou", "")
        binding.gametitel.text = "「$jinrou」です！"


        loadingDelay()
    }
    fun loadingDelay(){
        Handler().postDelayed(
            {
                var intent = Intent(this, CenterofMeetingandVoting::class.java)
                startActivity(intent)
            },
            2000,
        )
    }
}

