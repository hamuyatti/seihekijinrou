package com.example.seihekijinrou

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings.Global.putString
import android.view.View
import androidx.core.content.edit
import com.example.seihekijinrou.databinding.ActivityHekientory4Binding
import com.example.seihekijinrou.databinding.ActivityResultBinding
import androidx.preference.PreferenceManager

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")


        var name1 = pref.getString("name1", "")
        var name2 = pref.getString("name2", "")
        var name3 = pref.getString("name3", "")
        var name4 = pref.getString("name4", "")

        var number = (1..4).random()

        var jinrou = if (number == 1) {
            seiheki1
        } else if (number == 2) {
            seiheki2
        } else if (number == 3) {
            seiheki3
        } else {
            seiheki4
        }

        var jinrouname = if (jinrou == seiheki1) {
            name1
        } else if (jinrou == seiheki2) {
            name2
        } else if (jinrou == seiheki3) {
            name3
        } else {
            name4
        }

        pref.edit {
            putString("jinrou", jinrou)
        }
        pref.edit {
            putString("jinrouname", jinrouname)


        }
        loadingDelay()

    }
    fun loadingDelay(){
        Handler().postDelayed(
            {
                val intent = Intent(this, Result2::class.java)
                startActivity(intent)
            },
            1000,
        )
    }
}

