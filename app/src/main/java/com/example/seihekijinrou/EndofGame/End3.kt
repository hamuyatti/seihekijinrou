package com.example.seihekijinrou.EndofGame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.Start.gamestart
import com.example.seihekijinrou.databinding.ActivityEnd3Binding

class End3 : AppCompatActivity() {
    private lateinit var binding:ActivityEnd3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnd3Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var name1 = pref.getString("name1", "")
        var name2 = pref.getString("name2", "")
        var name3 = pref.getString("name3", "")
        var name4 = pref.getString("name4", "")
        var name5 = pref.getString("name5", "")

        var seiheki1 = pref.getString("seiheki1","")
        var seiheki2 = pref.getString("seiheki2","")
        var seiheki3 = pref.getString("seiheki3","")
        var seiheki4 = pref.getString("seiheki4","")
        var seiheki5 = pref.getString("seiheki5","")

        binding.endname6.text =name1
        binding.endname7.text =name2
        binding.endname8.text =name3
        binding.endname9.text =name4
        binding.endname10.text =name5

        binding.endheki6.text =seiheki1
        binding.endheki7.text =seiheki2
        binding.endheki8.text =seiheki3
        binding.endheki9.text =seiheki4
        binding.endheki10.text =seiheki5

        binding.backbutton.setOnClickListener {
            var intent = Intent(this, gamestart::class.java)
            startActivity(intent)
        }
    }
}