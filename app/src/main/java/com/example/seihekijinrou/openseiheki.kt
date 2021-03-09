package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityOpenseihekiBinding

class openseiheki : AppCompatActivity() {
    private lateinit var binding :ActivityOpenseihekiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOpenseihekiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var Suspect1 = pref.getString("Suspect1", "")


        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")

        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")

        var Suspecterseiheki =  if(Suspect1 == candidate1){
            seiheki1
        }else if (Suspect1 ==candidate2){
            seiheki2
        }else if (Suspect1 == candidate3){
            seiheki3
        }else {
            seiheki4
        }
        binding.Suspectername1.text = "$Suspect1 さんの性癖は${Suspecterseiheki}でした。"

        binding.button2.setOnClickListener{
                val intent = Intent(this, SecondMeetingTime::class.java)
                startActivity(intent)
        }

    }
}