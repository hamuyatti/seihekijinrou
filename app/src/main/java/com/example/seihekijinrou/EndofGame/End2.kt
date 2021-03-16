package com.example.seihekijinrou.EndofGame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityEnd2Binding

class End2 : AppCompatActivity() {
    private lateinit var binding:ActivityEnd2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnd2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var name6 = pref.getString("name6", "")
        var name7 = pref.getString("name7", "")
        var name8 = pref.getString("name8", "")
        var name9 = pref.getString("name9", "")
        var name10 = pref.getString("name10", "")


        var seiheki6 = pref.getString("seiheki6","")
        var seiheki7 = pref.getString("seiheki7","")
        var seiheki8 = pref.getString("seiheki8","")
        var seiheki9 = pref.getString("seiheki9","")
        var seiheki10 = pref.getString("seiheki10","")


        binding.endname6.text =name6
        binding.endname7.text =name7
        binding.endname8.text =name8
        binding.endname9.text =name9
        binding.endname10.text =name10

        binding.endheki6.text =seiheki6
        binding.endheki7.text =seiheki7
        binding.endheki8.text =seiheki8
        binding.endheki9.text =seiheki9
        binding.endheki10.text =seiheki10



        binding.backbutton.setOnClickListener {
            var intent = Intent(this,End3::class.java)
            startActivity(intent)
        }


    }
}