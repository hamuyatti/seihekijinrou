package com.example.seihekijinrou.MeetingandVoting.Voting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.ResultofVoting.falseResult
import com.example.seihekijinrou.ResultofVoting.trueResult
import com.example.seihekijinrou.databinding.ActivityVoting8Binding


class Voting8 : AppCompatActivity() {
    private lateinit var binding: ActivityVoting8Binding
    private lateinit var Suspect8: String
    private lateinit var remainmembers7: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String
    private lateinit var candidate5: String
    private lateinit var candidate6: String
    private lateinit var candidate7: String
    private lateinit var candidate8: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoting8Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")

        var fake = pref.getStringSet("remainmembers9", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]
            candidate7 = members[6]
            candidate8 = members[7]


            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7
            binding.name8.text = candidate8


        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect8 = candidate1

                R.id.name2 -> Suspect8 = candidate2

                R.id.name3 -> Suspect8 = candidate3

                R.id.name4 -> Suspect8 = candidate4

                R.id.name5 -> Suspect8 = candidate5

                R.id.name6 -> Suspect8 = candidate6

                R.id.name7 -> Suspect8 = candidate7

                R.id.name8 -> Suspect8 = candidate8
            }

            members.remove(Suspect8)
            remainmembers7 = members.toSet()

            binding.judge.setOnClickListener {
                if (Suspect8 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putStringSet("remainmembers7", remainmembers7)
                        putString("Suspect8", Suspect8)
                    }.apply { }
                    var intent = Intent(this, trueResult::class.java)
                    startActivity(intent)

                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putStringSet("remainmembers7", remainmembers7)
                        putString("Suspect8", Suspect8)
                    }.apply { }
                    var intent = Intent(this, falseResult::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
