package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seihekijinrou.databinding.ActivityTrueEnd1Binding

class trueEnd1 : AppCompatActivity() {
    private lateinit var binding:ActivityTrueEnd1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTrueEnd1Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            var intent = Intent(this, End2::class.java)
            startActivity(intent)
        }
        binding.backtotitle.setOnClickListener {
            var intent = Intent(this, explanation::class.java)
            startActivity(intent)
        }
    }
}