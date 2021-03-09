package com.example.seihekijinrou

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
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
           var jinrou = pref.getString("jinrou", "")
           binding.gametitle.text =  "「$jinrou」"

           binding.timertext.text = "2:00"
           var timer = MyCountDownTimer(2*60*1000,100)
           timer.start()

           binding.Meetingstop.setOnClickListener{
               val intent = Intent(this, ResultMeeting::class.java)
           startActivity(intent)
       }

    }
}