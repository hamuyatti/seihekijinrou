package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seihekijinrou.databinding.ActivityFakeBinding

class fake : AppCompatActivity() {
    private lateinit var binding:ActivityFakeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFakeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button3.setOnClickListener {
            var intent = Intent(this,ResultMeeting3::class.java)
            startActivity(intent)
        }

    }
}