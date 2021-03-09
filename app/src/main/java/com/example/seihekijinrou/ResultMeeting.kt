package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeetingBinding

class ResultMeeting : AppCompatActivity() {
    private lateinit var binding:ActivityResultMeetingBinding
    private lateinit var Suspect1:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrouname = pref.getString("jinrouname", "")

        binding.name1.text = pref.getString("name1", "")
        binding.name2.text = pref.getString("name2", "")
        binding.name3.text = pref.getString("name3", "")
        binding.name4.text = pref.getString("name4", "")

        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")


        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text ="$jinrou は誰の性癖？？"


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect1 = candidate1.toString()

                R.id.name2 -> Suspect1 = candidate2.toString()

                R.id.name3 -> Suspect1= candidate3.toString()

                R.id.name4-> Suspect1 = candidate4.toString()


            }
            binding.judge.setOnClickListener {
                if (Suspect1 == jinrouname) {
                    truejudgetime()

                } else {
                    falsejudgetime()
                }
            }
        }

    }
    fun truejudgetime(){
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { putString("Suspect1",Suspect1) }
        var intent = Intent(this, trueResult::class.java)
        startActivity(intent)
    }
    fun falsejudgetime(){
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { putString("Suspect1",Suspect1) }
        var intent = Intent(this, falseResult::class.java)
        startActivity(intent)
    }
 }
