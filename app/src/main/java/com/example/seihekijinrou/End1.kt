package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seihekijinrou.databinding.ActivityEnd1Binding

class End1 : AppCompatActivity() {
    private lateinit var binding:ActivityEnd1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnd1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener{
            var intent = Intent(this, End2::class.java)
            startActivity(intent)
        }
        binding.backtotitle.setOnClickListener{
            var intent = Intent(this, gamestart::class.java)
            startActivity(intent)
        }
    }
}