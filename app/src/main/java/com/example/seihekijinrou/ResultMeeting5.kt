package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeeting5Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting6Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting9Binding

class ResultMeeting5 : AppCompatActivity() {
    private lateinit var binding:ActivityResultMeeting5Binding
    private lateinit var Suspect5: String
    private lateinit var remainmembers4: MutableSet<String?>
    private lateinit var members:MutableList<String?>
    private lateinit var candidate1:String
    private lateinit var candidate2:String
    private lateinit var candidate3:String
    private lateinit var candidate4:String
    private lateinit var candidate5:String





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeeting5Binding.inflate(layoutInflater)
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
            candidate5 = members[4].toString()




            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5


        }


        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect5 = candidate1.toString()

                R.id.name2 -> Suspect5 = candidate2.toString()

                R.id.name3 -> Suspect5 = candidate3.toString()

                R.id.name4 -> Suspect5 = candidate4.toString()

                R.id.name5 -> Suspect5 = candidate5.toString()



            }
        }
        binding.judge.setOnClickListener {
            if (Suspect5 == jinrouname) {
                truejudgetime()

            } else {
                falsejudgetime()
            }
        }
    }

    fun truejudgetime() {
        remainmembers4;members.remove(Suspect5)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {putStringSet("remainmembers4",remainmembers4)
                   putString("Suspect5","")}.apply {  }
        var intent = Intent(this, trueResult::class.java)
        startActivity(intent)
    }

    fun falsejudgetime() {
        remainmembers4;members.remove(Suspect5)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { putStringSet("remainmembers4",remainmembers4)
                     putString("Suspect5","") }.apply {  }
        var intent = Intent(this, falseResult::class.java)
        startActivity(intent)
    }

}