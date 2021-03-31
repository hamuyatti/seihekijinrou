package com.example.seihekijinrou.Start

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seihekijinrou.databinding.ActivitySeihekiofpastBinding
import io.realm.Realm
import io.realm.kotlin.where

class SeihekiofPast : AppCompatActivity() {
    private lateinit  var binding:ActivitySeihekiofpastBinding
    private lateinit var realm:Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySeihekiofpastBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        realm = Realm.getDefaultInstance()


        binding.list.layoutManager = LinearLayoutManager(this)
        val Replacement = realm.where<seihekidata>().findAll()
        val adapter = Seihekiadapter(Replacement)
        binding.list.adapter = adapter

    }


    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}