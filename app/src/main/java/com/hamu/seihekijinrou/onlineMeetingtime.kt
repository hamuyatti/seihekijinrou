package com.hamu.seihekijinrou

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.databinding.FragmentMeetingtimeBinding
import com.hamu.seihekijinrou.databinding.FragmentOnlineMeetingtimeBinding
import com.hamu.seihekijinrou.databinding.FragmentOnlinefirstMeetingBinding


class onlineMeetingtime : Fragment() {
    private var _binding: FragmentOnlineMeetingtimeBinding? = null
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

            var remainmembers = pref.getStringSet("remainmembers", setOf(""))

            var wheretogo= remainmembers?.size

            when(wheretogo){
                3->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting3)
                4->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting4)
                5->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting5)
                6->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting6)
                7->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting7)
                8->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting8)
                9->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting9)
               10->findNavController().navigate(R.id.action_onlineMeetingtime_to_onlineVoting10)
            }

        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineMeetingtimeBinding.inflate(layoutInflater,container,false)
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

    override fun onPause() {
        super.onPause()

        soundPool.release()
    }


}