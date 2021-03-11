package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityOnlyFirstMeetingtimeBinding

class OnlyFirstMeetingtime : AppCompatActivity() {
    private lateinit var binding: ActivityOnlyFirstMeetingtimeBinding
    private lateinit var remainmembers3: Set<String>
    private lateinit var remainmembers4: Set<String>
    private lateinit var remainmembers5: Set<String>
    private lateinit var remainmembers6: Set<String>
    private lateinit var remainmembers7: Set<String>
    private lateinit var remainmembers8: Set<String>
    private lateinit var remainmembers9: Set<String>
    private lateinit var remainmembers10: Set<String>
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
        binding = ActivityOnlyFirstMeetingtimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)

        var jinrou = pref.getString("jinrou", "")
        binding.gametitle.text =  "「$jinrou」"


        binding.timertext.text = "2:00"
        var timer = MyCountDownTimer(2*60*1000,100)
        timer.start()

        var name1 = pref.getString("name1","").toString()
        var name2 = pref.getString("name2","").toString()
        var name3 = pref.getString("name3","").toString()
        var name4 = pref.getString("name4","").toString()
        var name5 = pref.getString("name5","").toString()
        var name6 = pref.getString("name6","").toString()
        var name7 = pref.getString("name7","").toString()
        var name8 = pref.getString("name8","").toString()
        var name9 = pref.getString("name9","").toString()
        var name10 = pref.getString("name10","").toString()

        binding.Meetingstop.setOnClickListener{
            if(name10.length==0&&name9.length==0&&name8.length==0&&name7.length==0&&name6.length==0
                &&name5.length==0&&name4.length ==0){
                remainmembers3 = setOf(name1,name2,name3)
                pref.edit{putStringSet("remainmembers3",remainmembers3)}.apply {  }
                var intent = Intent(this, ResultMeeting3::class.java)
                startActivity(intent)
            }
            else if(name10.length==0&&name9.length==0&&name8.length==0&&name7.length==0&&name6.length==0
                    &&name5.length==0) {
                remainmembers4 = setOf(name1, name2, name3, name4)
                pref.edit { putStringSet("remainmembers4", remainmembers4) }.apply {  }
                var intent = Intent(this, ResultMeeting4::class.java)
                startActivity(intent)
            }
            else if(name10.length==0&&name9.length==0&&name8.length==0&&name7.length==0&&name6.length==0){
                remainmembers5 = setOf(name1,name2,name3,name4,name5)
                pref.edit{putStringSet("remainmembers5",remainmembers5)}.apply {  }
                var intent = Intent(this, ResultMeeting5::class.java)
                startActivity(intent)
            }
            else if(name10.length==0&&name9.length==0&&name8.length==0&&name7.length==0){
                remainmembers6 = setOf(name1,name2,name3,name4,name5,name6)
                pref.edit{putStringSet("remainmembers6",remainmembers6)}.apply {  }
                var intent = Intent(this, ResultMeeting6::class.java)
                startActivity(intent)
            }
            else if(name10.length==0&&name9.length==0&&name8.length==0){
                remainmembers7 = setOf(name1,name2,name3,name4,name5,name6,name7)
                pref.edit{putStringSet("remainmembers7",remainmembers7)}.apply {  }
                var intent = Intent(this, ResultMeeting7::class.java)
                startActivity(intent)
            }
            else if(name10.length==0&&name9.length==0){
                remainmembers8 = setOf(name1,name2,name3,name4,name5,name6,name7,name8)
                pref.edit{putStringSet("remainmembers8",remainmembers8)}.apply {  }
                var intent = Intent(this, ResultMeeting8::class.java)
                startActivity(intent)
            }
            else if(name10.length==0){
                remainmembers9 = setOf(name1,name2,name3,name4,name5,name6,name7,name8,name9)
                pref.edit{putStringSet("remainmembers9",remainmembers9)}.apply {  }
                var intent = Intent(this, ResultMeeting9::class.java)
                startActivity(intent)
            }
            else{
                var intent = Intent(this, ResultMeeting10::class.java)
                startActivity(intent)
            }

        }
    }

}
