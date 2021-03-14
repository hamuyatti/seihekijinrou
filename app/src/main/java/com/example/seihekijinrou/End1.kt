package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityEnd1Binding

class End1 : AppCompatActivity() {
    private lateinit var binding:ActivityEnd1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnd1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var name1 = pref.getString("name1", "").toString()
        var name2 = pref.getString("name2", "").toString()
        var name3 = pref.getString("name3", "").toString()
        var name4 = pref.getString("name4", "").toString()
        var name5 = pref.getString("name5", "").toString()
        var name6 = pref.getString("name6", "").toString()

        binding.button4.setOnClickListener {
            if ((name1.length > 0 || name2.length > 0 || name3.length > 0 || name4.length > 0 || name5.length > 0) && name6.length == 0) {
                var intent = Intent(this, End3::class.java)
                startActivity(intent)
            } else {
                var intent = Intent(this, End2::class.java)
                startActivity(intent)
            }
            binding.backtotitle.setOnClickListener {
                var intent = Intent(this, gamestart::class.java)
                startActivity(intent)
            }
        }
    }
}