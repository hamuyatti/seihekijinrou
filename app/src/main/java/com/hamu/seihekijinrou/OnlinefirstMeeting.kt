package com.hamu.seihekijinrou

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.databinding.FragmentOnlyFirstMeetingTimeBinding

class OnlinefirstMeeting : Fragment() {
    private var _binding: FragmentOnlyFirstMeetingTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    lateinit var numberofpeople:String

    private lateinit var remainmembers3: Set<String>
    private lateinit var remainmembers4: Set<String>
    private lateinit var remainmembers5: Set<String>
    private lateinit var remainmembers6: Set<String>
    private lateinit var remainmembers7: Set<String>
    private lateinit var remainmembers8: Set<String>
    private lateinit var remainmembers9: Set<String>

    private lateinit var name1:String
    private lateinit var name2:String
    private lateinit var name3:String
    private lateinit var name4:String
    private lateinit var name5:String
    private lateinit var name6:String
    private lateinit var name7:String
    private lateinit var name8:String
    private lateinit var name9:String



    inner class MyCountDownTimer(millsInfuture: Long, countDownInterval: Long) :
        CountDownTimer(millsInfuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.timertext.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.timertext.text = "0:00"
            soundPool.play(soundResId,1.0f,1.0f,0,0,1.0f)
            toVoting()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlyFirstMeetingTimeBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)

        var jinrou = pref.getString("jinrouseiheki", "")
        binding.gametitle.text = "「$jinrou」"


        binding.timertext.text = "2:00"
        var timer = MyCountDownTimer(2 * 60 * 1000, 100)
        timer.start()

        name1 = pref.getString("name1", "").toString()
        name2 = pref.getString("name2", "").toString()
        name3 = pref.getString("name3", "").toString()
        name4 = pref.getString("name4", "").toString()
        name5 = pref.getString("name5", "").toString()
        name6 = pref.getString("name6", "").toString()
        name7 = pref.getString("name7", "").toString()
        name8 = pref.getString("name8", "").toString()
        name9 = pref.getString("name9", "").toString()


        numberofpeople = pref.getString("numberofpeople","" ).toString()

        binding.Meetingstop.setOnClickListener {
            toVoting()
        }

        return binding.root
    }


    fun toVoting() {

        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        if (numberofpeople == "3") {
            remainmembers3 = setOf(name1,name2,name3)
            pref.edit { putStringSet("remainmembers3", remainmembers3) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting3)

        } else if (numberofpeople == "4") {
            remainmembers4 = setOf(name1, name2, name3, name4)
            pref.edit { putStringSet("remainmembers4", remainmembers4) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting4)

        } else if (numberofpeople == "5") {
            remainmembers5 = setOf(name1, name2, name3, name4, name5)
            pref.edit { putStringSet("remainmembers5", remainmembers5) }.apply { }


            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting5)

        } else if (numberofpeople == "6") {
            remainmembers6 = setOf(name1, name2, name3, name4, name5, name6)
            pref.edit { putStringSet("remainmembers6", remainmembers6) }.apply { }


            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting6)

        } else if (numberofpeople == "7") {
            remainmembers7 = setOf(name1, name2, name3, name4, name5, name6, name7)
            pref.edit { putStringSet("remainmembers7", remainmembers7) }.apply { }

            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting7)

        } else if (numberofpeople == "8") {
            remainmembers8 = setOf(name1, name2, name3, name4, name5, name6, name7, name8)
            pref.edit { putStringSet("remainmembers8", remainmembers8) }.apply { }

            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting8)

        } else if (numberofpeople == "9") {
            remainmembers9 =
                setOf(name1, name2, name3, name4, name5, name6, name7, name8, name9)
            pref.edit { putStringSet("remainmembers9", remainmembers9) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting9)

        } else {

            /*remainmembersは誰が残っているか特定するための配列なので、人数いっぱいの１０人の時は自明なので使いません*/
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting3)
        }
    }

    override fun onResume() {
        super.onResume()
        soundPool =
            SoundPool.Builder().run {
                val audioAttributes = AudioAttributes.Builder().run {
                    setUsage(AudioAttributes.USAGE_MEDIA)
                    build()
                }
                setMaxStreams(1)
                setAudioAttributes(audioAttributes)
                build()
            }
        soundResId = soundPool.load(context, R.raw.pigeon, 1)


    }
}
