package com.hamu.seihekijinrou.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.R

class gamestart : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamestart)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            clear()
            .apply()
        }

        loadingDelay()




    }

    fun loadingDelay(){
        Handler().postDelayed(
                {
                    val intent = Intent(this, explanation::class.java)
                    startActivity(intent)
                },
                1000,
        )
    }
}