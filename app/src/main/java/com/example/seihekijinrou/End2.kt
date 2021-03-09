package com.example.seihekijinrou

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
        var name1 = pref.getString("name1", "")
        var name2 = pref.getString("name2", "")
        var name3 = pref.getString("name3", "")
        var name4 = pref.getString("name4", "")

        var seiheki1 = pref.getString("seiheki1","")
        var seiheki2 = pref.getString("seiheki2","")
        var seiheki3 = pref.getString("seiheki3","")
        var seiheki4 = pref.getString("seiheki4","")


        binding.endname1.text =name1
        binding.endname2.text =name2
        binding.endname3.text =name3
        binding.endname4.text =name4

        binding.endheki1.text =seiheki1
        binding.endheki2.text =seiheki2
        binding.endheki3.text =seiheki3
        binding.endheki4.text =seiheki4


        binding.backbutton.setOnClickListener {
            var intent = Intent(this,gamestart::class.java)
            startActivity(intent)
        }


    }
}