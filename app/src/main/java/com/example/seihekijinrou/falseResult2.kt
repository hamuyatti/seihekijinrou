package com.example.seihekijinrou

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityFalseResult2Binding

class falseResult2 : AppCompatActivity() {
    private lateinit var binding: ActivityFalseResult2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFalseResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var Suspect1 = pref.getString("Suspect1", "")


        binding.check.text = "{$Suspect1}さんの性癖を公開しますか？"
        binding.Yes.setOnClickListener {
            Yesbutton()
        }
        binding.No.setOnClickListener {
            Nobutton()
        }


    }


    fun Yesbutton() {
        var intent = Intent(this, openseiheki::class.java)
        startActivity(intent)

    }

    fun Nobutton() {

        var intent = Intent(this, SecondMeetingTime::class.java)
        startActivity(intent)
    }
}
