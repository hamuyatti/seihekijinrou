package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityResultMeeting6Binding
import com.example.seihekijinrou.databinding.ActivityResultMeeting7Binding

class ResultMeeting6 : AppCompatActivity() {
    private lateinit var binding: ActivityResultMeeting6Binding
    private lateinit var Suspect6: String
    private lateinit var remainmembers5: MutableSet<String?>
    private lateinit var members:MutableList<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultMeeting6Binding.inflate(layoutInflater)
        setContentView(binding.root)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")

        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")
        var fake: MutableSet<String?> = mutableSetOf("データを取得できていません")
        members= pref.getStringSet("remainmembers6",fake) as MutableList<String?>
        /*defvalueのエラーを解決できなかったためfakeを追加して応急処置しました*/



        var candidate1 = members[0]
        var candidate2 = members[1]
        var candidate3 = members[2]
        var candidate4 = members[3]
        var candidate5 = members[4]
        var candidate6 = members[5]


        binding.name6.text = candidate6
        binding.name5.text = candidate5
        binding.name4.text = candidate4
        binding.name3.text = candidate3
        binding.name2.text = candidate2
        binding.name1.text = candidate1




        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect6 = candidate1.toString()

                R.id.name2 -> Suspect6 = candidate2.toString()

                R.id.name3 -> Suspect6 = candidate3.toString()

                R.id.name4 -> Suspect6 = candidate4.toString()

                R.id.name5 -> Suspect6 = candidate5.toString()

                R.id.name6 -> Suspect6 = candidate6.toString()




            }
        }
        binding.judge.setOnClickListener {
            if (Suspect6 == jinrouname) {
                truejudgetime()

            } else {
                falsejudgetime()
            }
        }
    }

    fun truejudgetime() {
        remainmembers5;members.remove(Suspect6)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {putStringSet("remainmembers5",remainmembers5)
                   putString("Suspect6","")}
        var intent = Intent(this, trueResult::class.java)
        startActivity(intent)
    }

    fun falsejudgetime() {
        remainmembers5;members.remove(Suspect6)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit { putStringSet("remainmembers5",remainmembers5)
                    putString("Suspect6","")}
        var intent = Intent(this, falseResult::class.java)
        startActivity(intent)
    }

}