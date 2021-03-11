package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeeting10Binding


class ResultMeeting10 : AppCompatActivity() {
    private lateinit var binding: ActivityResultMeeting10Binding
    private lateinit var Suspect10:String
    private lateinit var remainmembers9:MutableSet<String>
    private lateinit var members:MutableSet<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeeting10Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrouname = pref.getString("jinrouname", "")

        binding.name10.text =pref.getString("name10", "")
        binding.name9.text = pref.getString("name9", "")
        binding.name8.text = pref.getString("name8", "")
        binding.name7.text = pref.getString("name7", "")
        binding.name6.text = pref.getString("name6", "")
        binding.name5.text = pref.getString("name5", "")
        binding.name4.text = pref.getString("name4", "")
        binding.name3.text = pref.getString("name3", "")
        binding.name2.text = pref.getString("name2", "")
        binding.name1.text = pref.getString("name1", "")

        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")
        var candidate5 = pref.getString("name5", "")
        var candidate6 = pref.getString("name6", "")
        var candidate7 = pref.getString("name7", "")
        var candidate8 = pref.getString("name8", "")
        var candidate9 = pref.getString("name9", "")
        var candidate10 =pref.getString("name10", "")


        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text ="$jinrou は誰の性癖？？"

        members;mutableSetOf(candidate1,candidate2,candidate3,candidate4,candidate5,candidate6,candidate7,candidate8,candidate9,candidate10)



        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect10 = candidate1.toString()
                R.id.name2 -> Suspect10 = candidate2.toString()
                R.id.name3 -> Suspect10 = candidate3.toString()
                R.id.name4 -> Suspect10 = candidate4.toString()
                R.id.name5 -> Suspect10 = candidate5.toString()
                R.id.name6 -> Suspect10 = candidate6.toString()
                R.id.name7 -> Suspect10 = candidate7.toString()
                R.id.name8 -> Suspect10 = candidate8.toString()
                R.id.name9 -> Suspect10 = candidate9.toString()
                R.id.name10 ->Suspect10 = candidate10.toString()


            }
            remainmembers9;members.remove(Suspect10)
            pref.edit { putStringSet("remainmembers9",remainmembers9)
                putString("Suspect10",Suspect10)}
            }

            binding.judge.setOnClickListener {
                if (Suspect10 == jinrouname) {
                    truejudgetime()

                } else {
                    falsejudgetime()
                }
            }


    }
    fun truejudgetime(){

        var intent = Intent(this, trueResult::class.java)
        startActivity(intent)
    }
    fun falsejudgetime(){

        var intent = Intent(this, falseResult::class.java)
        startActivity(intent)
    }
}