package com.example.seihekijinrou

import android.content.Intent
import android.media.AudioAttributes
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityFalseResult2Binding

class falseResult2 : AppCompatActivity() {
    private lateinit var binding: ActivityFalseResult2Binding
    private lateinit var ThistimeSuspect:String
    private lateinit var Suspect3:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFalseResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var jinrou = pref.getString("jinrou", "")

        var Suspect10= pref.getString("Suspect10","") as String
        var Suspect9 =  pref.getString("Suspect9","") as String
        var Suspect8 =  pref.getString("Suspect8","") as String
        var Suspect7 =  pref.getString("Suspect7","") as String
        var Suspect6 =  pref.getString("Suspect6","") as String
        var Suspect5 =  pref.getString("Suspect5","") as String
        var Suspect4 =  pref.getString("Suspect4","") as String
        var Suspect3 =  pref.getString("Suspect3","") as String

         ThistimeSuspect=if(Suspect10.isNotEmpty()&&Suspect9.length ==0){
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




        binding.check.text = "$ThistimeSuspect さんの性癖を公開しますか？"
        binding.Yes.setOnClickListener {
            var intent = Intent(this, openseiheki::class.java)
            startActivity(intent)

        }



        binding.No.setOnClickListener {
            if(Suspect3.length>0){
                var intent = Intent(this,End1::class.java)
                startActivity(intent)}
            else{
                var intent = Intent(this, Meetingtime::class.java)
                startActivity(intent)
            }
        }
    }
}
