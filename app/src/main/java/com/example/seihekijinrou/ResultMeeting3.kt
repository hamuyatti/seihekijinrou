package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeeting3Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting4Binding

class ResultMeeting3 : AppCompatActivity() {
    private lateinit var binding: ActivityResultMeeting3Binding
    private lateinit var Suspect3: String
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeeting3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")

        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")
        var fake = pref.getStringSet("remainmembers3", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect3 = candidate1

                R.id.name2 -> Suspect3 = candidate2

                R.id.name3 -> Suspect3 = candidate3

            }

            members.remove(Suspect3)

            binding.judge.setOnClickListener {
                if (Suspect3 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putString("Suspect3", Suspect3)
                    }.apply { }
                    var intent = Intent(this, trueResult::class.java)
                    startActivity(intent)

                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putString("Suspect3", Suspect3)
                    }.apply { }
                    var intent = Intent(this, falseResult::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
