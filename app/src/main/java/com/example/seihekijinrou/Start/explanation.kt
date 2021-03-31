package com.example.seihekijinrou.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.Preparation.numberofpeople

import com.example.seihekijinrou.databinding.ActivityExplanationBinding

class explanation : AppCompatActivity() {
    private lateinit var binding: ActivityExplanationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityExplanationBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            clear()
                .apply()
        }

        binding.start.setOnClickListener {
            var intent = Intent(this, numberofpeople::class.java)
            startActivity(intent)

        }
        binding.checkrules.setOnClickListener {
            var intent = Intent(this, checkrules::class.java)
            startActivity(intent)
        }
            binding.button.setOnClickListener {
            var intent = Intent(this, SeihekiofPast::class.java)
            startActivity(intent)
        }


    }
}