package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivitySecondOpenSeihekiBinding

class SecondOpenSeiheki : AppCompatActivity() {
    private lateinit var binding:ActivitySecondOpenSeihekiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondOpenSeihekiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var Suspect2 = pref.getString("Suspect2", "")


        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")

        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")

        var Suspecterseiheki =  if(Suspect2 == candidate1){
            seiheki1
        }else if (Suspect2 ==candidate2){
            seiheki2
        }else if (Suspect2 == candidate3){
            seiheki3
        }else {
            seiheki4
        }
        binding.Suspectername2.text = "$Suspect2 さんの性癖は${Suspecterseiheki}でした。"

        binding.button3.setOnClickListener{
            val intent = Intent(this, End1::class.java)
            startActivity(intent)
        }

    }
}