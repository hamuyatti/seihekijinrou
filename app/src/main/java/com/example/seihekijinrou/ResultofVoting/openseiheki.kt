/*package com.example.seihekijinrou.ResultofVoting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.EndofGame.End1ofBad
import com.example.seihekijinrou.Meetingtime
import com.example.seihekijinrou.databinding.ActivityOpenseihekiBinding

class openseiheki : AppCompatActivity() {
    private lateinit var binding :ActivityOpenseihekiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityOpenseihekiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")
        var candidate5 = pref.getString("name5", "")
        var candidate6 = pref.getString("name6", "")
        var candidate7 = pref.getString("name7", "")
        var candidate8 = pref.getString("name8", "")
        var candidate9 = pref.getString("name9", "")
        var candidate10 = pref.getString("name10", "")

        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")
        var seiheki5 = pref.getString("seiheki5", "")
        var seiheki6 = pref.getString("seiheki6", "")
        var seiheki7 = pref.getString("seiheki7", "")
        var seiheki8 = pref.getString("seiheki8", "")
        var seiheki9 = pref.getString("seiheki9", "")
        var seiheki10 = pref.getString("seiheki10", "")


        var Suspect10= pref.getString("Suspect10","") as String
        var Suspect9 =  pref.getString("Suspect9","") as String
        var Suspect8 =  pref.getString("Suspect8","") as String
        var Suspect7 =  pref.getString("Suspect7","") as String
        var Suspect6 =  pref.getString("Suspect6","") as String
        var Suspect5 =  pref.getString("Suspect5","") as String
        var Suspect4 =  pref.getString("Suspect4","") as String
        var Suspect3 =  pref.getString("Suspect3","") as String

        var Rustmember= pref.getString("checkforlose","")

        /*その回で誰が選ばれたのか、どんな性癖を入力したかを洗い出しています*/
        var ThistimeSuspect=if(Suspect10.isNotEmpty()&&Suspect9.length ==0){
            Suspect10
        }else if(Suspect9.isNotEmpty()&&Suspect8.length==0){
            Suspect9
        }else if(Suspect8.isNotEmpty()&&Suspect7.length==0){
            Suspect8
        }else if(Suspect7.isNotEmpty()&&Suspect6.length==0){
            Suspect7
        }else if(Suspect6.isNotEmpty()&&Suspect5.length==0){
            Suspect6
        }else if(Suspect5.isNotEmpty()&&Suspect4.length==0){
            Suspect5
        }else if(Suspect4.isNotEmpty()&&Suspect3.length==0){
            Suspect4
        }else {
            Suspect3
        }

        var Suspecterseiheki =  if(ThistimeSuspect == candidate1){
            seiheki1
        }else if (ThistimeSuspect ==candidate2){
            seiheki2
        }else if (ThistimeSuspect == candidate3){
            seiheki3
        }else if (ThistimeSuspect ==candidate4){
            seiheki4
        }else if (ThistimeSuspect == candidate5){
            seiheki5
        }else if (ThistimeSuspect ==candidate6){
            seiheki6
        }else if (ThistimeSuspect == candidate7){
            seiheki7
        }else if (ThistimeSuspect ==candidate8){
            seiheki8
        }else if (ThistimeSuspect == candidate9){
            seiheki9
        }else{
            seiheki10
        }


        binding.Suspectername1.text = "$ThistimeSuspect さんの性癖は${Suspecterseiheki}でした。"

        binding.button2.setOnClickListener{
                if(Rustmember?.isNotEmpty() == true){
                    val intent = Intent(this, End1ofBad::class.java)
                    startActivity(intent)
                }else {
                val intent = Intent(this, Meetingtime::class.java)
                startActivity(intent)
                }
        }

    }
}*/