package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivitySecondtrueResult2Binding

class secondtrueResult2 : AppCompatActivity() {
    private lateinit var binding:ActivitySecondtrueResult2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySecondtrueResult2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrouseiheki = pref.getString("jinrou","")
        var jinrou = pref.getString("Suspect2","")
        binding.openjinrou.text = "${jinrou}さんは性癖「${jinrouseiheki}」の持ち主です！"

        binding.yes.setOnClickListener {
            var intent = Intent(this,End2::class.java)
                startActivity(intent)
        }
        binding.no.setOnClickListener {
            var intent = Intent(this,gamestart::class.java)
            startActivity(intent)
        }
    }
}