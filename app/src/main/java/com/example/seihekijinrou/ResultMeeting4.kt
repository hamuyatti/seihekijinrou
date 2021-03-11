package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeeting4Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting5Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting9Binding

class ResultMeeting4 : AppCompatActivity() {
    private lateinit var binding: ActivityResultMeeting4Binding
    private lateinit var Suspect4: String
    private lateinit var remainmembers3: MutableSet<String?>
    private lateinit var members:MutableList<String?>
    private lateinit var candidate1:String
    private lateinit var candidate2:String
    private lateinit var candidate3:String
    private lateinit var candidate4:String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeeting4Binding.inflate(layoutInflater)
        setContentView(binding.root)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")

        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")
        var fake= pref.getStringSet("remainmembers3", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0].toString()
            candidate2 = members[1].toString()
            candidate3 = members[2].toString()
            candidate4 = members[3].toString()




            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4


        }





        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect4 = candidate1.toString()

                R.id.name2 -> Suspect4 = candidate2.toString()

                R.id.name3 -> Suspect4 = candidate3.toString()

                R.id.name4 -> Suspect4 = candidate4.toString()


            }
        }
        binding.judge.setOnClickListener {
            if (Suspect4 == jinrouname) {
                truejudgetime()

            } else {
                falsejudgetime()
            }
        }
    }

    fun truejudgetime() {
        remainmembers3;members.remove(Suspect4)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {putStringSet("remainmembers3",remainmembers3)
                   putString("Suspect4",Suspect4)}
        var intent = Intent(this, trueResult::class.java)
        startActivity(intent)
    }

    fun falsejudgetime() {
        remainmembers3;members.remove(Suspect4)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { putStringSet("remainmembers3",remainmembers3)
                    putString("Suspect4", Suspect4)}
        var intent = Intent(this, falseResult::class.java)
        startActivity(intent)
    }

}