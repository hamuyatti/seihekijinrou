package com.example.seihekijinrou.Preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.MeetingandVoting.OnlyFirstMeetingtime
import com.example.seihekijinrou.databinding.ActivityResult2Binding



class Result2 : AppCompatActivity() {
    private lateinit var binding: ActivityResult2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")
        binding.gametitel.text = "「$jinrou」です！"

        binding.button.setOnClickListener {

            val intent = Intent(this, OnlyFirstMeetingtime::class.java)
            startActivity(intent)


        }
    }
}