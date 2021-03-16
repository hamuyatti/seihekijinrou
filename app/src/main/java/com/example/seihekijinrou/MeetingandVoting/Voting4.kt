package com.example.seihekijinrou.MeetingandVoting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.ResultofVoting.falseResult
import com.example.seihekijinrou.ResultofVoting.trueResult
import com.example.seihekijinrou.databinding.ActivityVoting4Binding


class Voting4 : AppCompatActivity() {
    private lateinit var binding: ActivityVoting4Binding
    private lateinit var Suspect4: String
    private lateinit var remainmembers3: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityVoting4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")

        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")
        var fake = pref.getStringSet("remainmembers4", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4


        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect4 = candidate1

                R.id.name2 -> Suspect4 = candidate2

                R.id.name3 -> Suspect4 = candidate3

                R.id.name4 -> Suspect4 = candidate4


            }

            members.remove(Suspect4)
            remainmembers3 = members.toSet()

            binding.judge.setOnClickListener {
                if (Suspect4 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putStringSet("remainmembers3", remainmembers3)
                        putString("Suspect4", Suspect4)
                    }.apply { }
                    var intent = Intent(this, trueResult::class.java)
                    startActivity(intent)

                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(this)
                    pref.edit {
                        putStringSet("remainmembers3", remainmembers3)
                        putString("Suspect4", Suspect4)
                    }.apply { }
                    var intent = Intent(this, falseResult::class.java)
                    startActivity(intent)
                }
            }
        }

    }
}
