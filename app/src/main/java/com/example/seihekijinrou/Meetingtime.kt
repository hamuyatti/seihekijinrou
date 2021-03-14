package com.example.seihekijinrou

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityMeetingtimeBinding

class Meetingtime : AppCompatActivity() {
    private lateinit var binding:ActivityMeetingtimeBinding
    inner class MyCountDownTimer(millsInfuture:Long,countDownInterval:Long):
        CountDownTimer(millsInfuture,countDownInterval){

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished/1000L/60L
            val second = millisUntilFinished/1000L%60L
            binding.timertext.text = "%1d:%2$02d".format(minute,second)
        }

        override fun onFinish() {
            binding.timertext.text = "0:00"
        }

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMeetingtimeBinding.inflate(layoutInflater)
                   setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
           pref.edit{
               remove("ThistimeSuspect").commit()
           }
           var jinrou = pref.getString("jinrou", "")
           binding.gametitle.text =  "「$jinrou」"
           binding.timertext.text = "2:00"
           var timer = MyCountDownTimer(2*60*1000,100)
           timer.start()

        var Suspect10= pref.getString("Suspect10","") as String
        var Suspect9 =  pref.getString("Suspect9","") as String
        var Suspect8 =  pref.getString("Suspect8","") as String
        var Suspect7 =  pref.getString("Suspect7","") as String
        var Suspect6 =  pref.getString("Suspect6","") as String
        var Suspect5 =  pref.getString("Suspect5","") as String
        var Suspect4 =  pref.getString("Suspect4","") as String
        var Suspect3 =  pref.getString("Suspect3","") as String


        binding.Meetingstop.setOnClickListener{
               if(Suspect4.isNotEmpty()&&Suspect3.length == 0){
                   var intent = Intent(this, ResultMeeting3::class.java)
                   startActivity(intent)
               }
              else if(Suspect5.isNotEmpty()&&Suspect4.length ==0){
                   var intent = Intent(this, ResultMeeting4::class.java)
                   startActivity(intent)
               }else if(Suspect6.isNotEmpty()&&Suspect5.length==0){
                   var intent = Intent(this, ResultMeeting5::class.java)
                   startActivity(intent)
               }else if(Suspect7.isNotEmpty()&&Suspect6.length==0){
                   var intent = Intent(this, ResultMeeting6::class.java)
                   startActivity(intent)
               }else if(Suspect8.isNotEmpty()&&Suspect7.length==0){
                   var intent = Intent(this, ResultMeeting7::class.java)
                   startActivity(intent)
               }else if(Suspect9.isNotEmpty()&&Suspect8.length==0){
                   var intent = Intent(this, ResultMeeting8::class.java)
                   startActivity(intent)
               }else if(Suspect10.isNotEmpty()&&Suspect9.length==0){
                   var intent = Intent(this, ResultMeeting9::class.java)
                   startActivity(intent)
               }else{
                   var intent = Intent(this, ResultMeeting10::class.java)
                   startActivity(intent)
               }


           }
       }

    }
