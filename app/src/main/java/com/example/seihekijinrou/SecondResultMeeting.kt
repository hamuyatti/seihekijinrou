package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeetingBinding
import com.example.seihekijinrou.databinding.ActivitySecondMeetingTimeBinding
import com.example.seihekijinrou.databinding.ActivitySecondResultMeetingBinding

class SecondResultMeeting : AppCompatActivity() {
    private lateinit var binding: ActivitySecondResultMeetingBinding
    private lateinit var Suspect2: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondResultMeetingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrouname = pref.getString("jinrouname", "")
        var suspect1 = pref.getString("Suspect1", "")

        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")

        if (suspect1 == candidate1) {
            binding.name1.text = pref.getString("name2", "")
            binding.name2.text = pref.getString("name3", "")
            binding.name3.text = pref.getString("name4", "")

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.name1 -> Suspect2 = candidate2.toString()

                    R.id.name2 -> Suspect2 = candidate3.toString()

                    R.id.name3 -> Suspect2 = candidate4.toString()

                }
            }
            binding.judge.setOnClickListener {
                if (Suspect2 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondtrueResult::class.java)
                    startActivity(intent)
                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondfalseResult::class.java)
                    startActivity(intent)
                }
            }


        } else if (suspect1 == candidate2) {
            binding.name1.text = pref.getString("name1", "")
            binding.name2.text = pref.getString("name3", "")
            binding.name3.text = pref.getString("name4", "")

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.name1 -> Suspect2 = candidate1.toString()

                    R.id.name2 -> Suspect2 = candidate3.toString()

                    R.id.name3 -> Suspect2 = candidate4.toString()

                }
            }
            binding.judge.setOnClickListener {
                if (Suspect2 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondtrueResult::class.java)
                    startActivity(intent)
                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondfalseResult::class.java)
                    startActivity(intent)
                }
            }


        } else if (suspect1 == candidate3) {
            binding.name1.text = pref.getString("name1", "")
            binding.name2.text = pref.getString("name2", "")
            binding.name3.text = pref.getString("name4", "")

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.name1 -> Suspect2 = candidate1.toString()

                    R.id.name2 -> Suspect2 = candidate2.toString()

                    R.id.name3 -> Suspect2 = candidate4.toString()

                }
            }
            binding.judge.setOnClickListener {
                if (Suspect2 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondtrueResult::class.java)
                    startActivity(intent)
                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondfalseResult::class.java)
                    startActivity(intent)
                }
            }
        } else {
            binding.name1.text = pref.getString("name1", "")
            binding.name2.text = pref.getString("name2", "")
            binding.name3.text = pref.getString("name3", "")

            binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.name1 -> Suspect2 = candidate1.toString()

                    R.id.name2 -> Suspect2 = candidate2.toString()

                    R.id.name3 -> Suspect2 = candidate3.toString()

                }
            }
            binding.judge.setOnClickListener {
                if (Suspect2 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondtrueResult::class.java)
                    startActivity(intent)
                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit { putString("Suspect2", Suspect2) }
                    var intent = Intent(this, secondfalseResult::class.java)
                    startActivity(intent)

                }
            }
        }
    }
}




