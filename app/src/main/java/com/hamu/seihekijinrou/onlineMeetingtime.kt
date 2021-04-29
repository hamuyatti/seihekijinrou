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
    var db = Firebase.firestore



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

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnlineMeetingtimeBinding.inflate(layoutInflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var collection = db.collection("$roomname")
        var MeetingStop = collection.document("会議終了")


        var jinrou = pref.getString("jinrou", "")
        binding.gametitle.text =  "「$jinrou」"
        binding.timertext.text = "30"
        var timer = MyCountDownTimer(30*1000,100)
        timer.start()

        binding.Meetingstop.setOnClickListener {
            var stop = hashMapOf("会議" to "終了しました")
            pref.edit {
                putString("check2","判別用")
            }.apply {}
            MeetingStop.set(stop)
            toVoting()
        }

        MeetingStop
                .addSnapshotListener{it,tmp->
                    var check:String? =  pref.getString("check2","")
                    if(check?.isEmpty()!!) {
                        if(it?.exists()!=false){
                            MeetingStop
                            toVoting()
                        }

                    }
                }

        return binding.root
    }

    fun toVoting(){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var remainmembers = pref.getStringSet("remainmembers", setOf(""))

        var timer = MyCountDownTimer(30*1000,100)
        timer.cancel()

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