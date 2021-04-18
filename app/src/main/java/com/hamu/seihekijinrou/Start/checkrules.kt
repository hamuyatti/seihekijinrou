package com.hamu.seihekijinrou.Start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.ActivityCheckrulesBinding


class checkrules : AppCompatActivity() {
    class MyAdapter(fa:FragmentActivity):FragmentStateAdapter(fa){
        private val resources = listOf(R.drawable.slide00, R.drawable.slide01,R.drawable.slide02,R.drawable.slide03,
        R.drawable.slide04,R.drawable.slide05,R.drawable.slide06,R.drawable.slide07,R.drawable.slide08)
        override fun getItemCount(): Int =resources.size

        override fun createFragment(position: Int): androidx.fragment.app.Fragment
            =checkrulesImeges.newInstance(resources[position])


    }

    private lateinit var binding: ActivityCheckrulesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCheckrulesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.pager.adapter=MyAdapter(this)

        binding.button5.setOnClickListener {
            var intent = Intent(this, gamestart::class.java)
            startActivity(intent)
        }
    }
}