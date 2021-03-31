package com.example.seihekijinrou.Start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.ActivitySeihekiRankingBinding

class SeihekiofPast : AppCompatActivity() {
    private lateinit  var binding:ActivitySeihekiRankingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySeihekiRankingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)






    }
}