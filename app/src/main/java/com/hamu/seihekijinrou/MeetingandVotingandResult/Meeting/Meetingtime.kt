package com.hamu.seihekijinrou.MeetingandVotingandResult.Meeting

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
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentMeetingtimeBinding


class Meetingtime : Fragment() {
    private var _binding:FragmentMeetingtimeBinding?=null
    private val binding get() = _binding!!
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    private lateinit var Suspect9:String
    private lateinit var Suspect8:String
    private lateinit var Suspect7:String
    private lateinit var Suspect6:String
    private lateinit var Suspect5:String
    private lateinit var Suspect4:String
    private lateinit var Suspect3:String

    inner class MyCountDownTimer(millsInfuture: Long, countDownInterval: Long) :
        CountDownTimer(millsInfuture, countDownInterval) {


        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.timertext.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            binding.timertext.text = "0:00"
            soundPool.play(soundResId,1.0f,1.0f,0,0,1.0f)
            Suspect9 =  pref.getString("Suspect9","") as String
            Suspect8 =  pref.getString("Suspect8","") as String
            Suspect7 =  pref.getString("Suspect7","") as String
            Suspect6 =  pref.getString("Suspect6","") as String
            Suspect5 =  pref.getString("Suspect5","") as String
            Suspect4 =  pref.getString("Suspect4","") as String
            Suspect3 =  pref.getString("Suspect3","") as String


            if (Suspect4.isNotEmpty() && Suspect3.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting3)
            } else if (Suspect5.isNotEmpty() && Suspect4.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting4)

            } else if (Suspect6.isNotEmpty() && Suspect5.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting5)

            } else if (Suspect7.isNotEmpty() && Suspect6.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting6)

            } else if (Suspect8.isNotEmpty() && Suspect7.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting7)

            } else if (Suspect9.isNotEmpty() && Suspect8.isEmpty()) {
                findNavController().navigate(R.id.action_meetingtime_to_voting8)

            } else {
                findNavController().navigate(R.id.action_meetingtime_to_voting9)

            }

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentMeetingtimeBinding.inflate(layoutInflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit{
            remove("ThistimeSuspect").apply()
        }

        Suspect9 =  pref.getString("Suspect9","") as String
        Suspect8 =  pref.getString("Suspect8","") as String
        Suspect7 =  pref.getString("Suspect7","") as String
        Suspect6 =  pref.getString("Suspect6","") as String
        Suspect5 =  pref.getString("Suspect5","") as String
        Suspect4 =  pref.getString("Suspect4","") as String
        Suspect3 =  pref.getString("Suspect3","") as String

        var jinrou = pref.getString("jinrou", "")
        binding.gametitle.text =  "「$jinrou」"
        binding.timertext.text = "2:00"
        var timer = MyCountDownTimer(2*60*1000,100)
        timer.start()

        binding.Meetingstop.setOnClickListener {
           toVoting()
        }
        return binding.root
    }

    fun toVoting(){
        if (Suspect4.isNotEmpty() && Suspect3.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting3)
        } else if (Suspect5.isNotEmpty() && Suspect4.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting4)

        } else if (Suspect6.isNotEmpty() && Suspect5.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting5)

        } else if (Suspect7.isNotEmpty() && Suspect6.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting6)

        } else if (Suspect8.isNotEmpty() && Suspect7.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting7)

        } else if (Suspect9.isNotEmpty() && Suspect8.length == 0) {
            findNavController().navigate(R.id.action_meetingtime_to_voting8)

        } else {
            findNavController().navigate(R.id.action_meetingtime_to_voting9)

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