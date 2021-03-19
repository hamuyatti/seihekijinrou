package com.example.seihekijinrou.Preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.seihekijinrou.Preparation.Result2
import com.example.seihekijinrou.R

class Result1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result1)
        loadingDelay()
    }
    fun loadingDelay(){
        Handler().postDelayed(
            {
                var intent = Intent(this, Result2::class.java)
                startActivity(intent)
            },
            1000,
        )
    }
}