package com.hamu.seihekijinrou.MeetingandVotingandResult.Online.Meeting

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
import com.hamu.seihekijinrou.Preparation.numberofpeople
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentOnlinefirstMeetingBinding

class OnlinefirstMeeting : Fragment() {
    private var _binding: FragmentOnlinefirstMeetingBinding? = null
    private val binding get() = _binding!!
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    lateinit var numberofpeople:String

    private lateinit var remainmembers: Set<String>


    private lateinit var name1:String
    private lateinit var name2:String
    private lateinit var name3:String
    private lateinit var name4:String
    private lateinit var name5:String
    private lateinit var name6:String
    private lateinit var name7:String
    private lateinit var name8:String
    private lateinit var name9:String
    private lateinit var name10:String

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
        _binding = FragmentOnlinefirstMeetingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loading.visibility = View.INVISIBLE
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var db = Firebase.firestore
        var collection = db.collection("$roomname")
        var Meeting = collection.document("会議状況")


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
        name10 = pref.getString("name10", "").toString()


        numberofpeople = pref.getString("numberofpeople","" ).toString()

        binding.Meetingstop.setOnClickListener {
            AlertDialog.Builder(requireContext())
                    .setMessage("会議を終了しますか？")
                    .setPositiveButton("はい") { dialog, which ->
                        timer.cancel()
                        pref.edit {
                            putString("check","判別用").commit()
                        }
                        var finishMeeting= hashMapOf("会議状況" to "終了しました" )
                        Meeting.set(finishMeeting)
                        toVoting()
                    }
                    .setNegativeButton("もどる"){dialog,which->
                        timer.start()
                    }
                    .show()
        }


        Meeting.addSnapshotListener{it,tmp->
            var check:String? =  pref.getString("check","")
            if(check?.isEmpty()!!) {
                collection
                        .whereEqualTo("会議状況", "終了しました")
                        .get()
                        .addOnSuccessListener {
                            if (!it!!.isEmpty) {
                                timer.cancel()
                                toVoting()
                            }

                        }
            }

        }

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



    fun toVoting() {

        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        if (numberofpeople =="3") {
            remainmembers = setOf(name1,name2,name3)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting3)

        } else if (numberofpeople =="4") {
            remainmembers = setOf(name1, name2, name3, name4)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting4)

        } else if (numberofpeople =="5") {
            remainmembers = setOf(name1, name2, name3, name4, name5)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }


            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting5)

        } else if (numberofpeople =="6") {
            remainmembers = setOf(name1, name2, name3, name4, name5, name6)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }


            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting6)

        } else if (numberofpeople =="7") {
            remainmembers = setOf(name1, name2, name3, name4, name5, name6, name7)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }

            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting7)

        } else if (numberofpeople =="8") {
            remainmembers = setOf(name1, name2, name3, name4, name5, name6, name7, name8)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }

            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting8)

        } else if (numberofpeople =="9") {
            remainmembers =
                setOf(name1, name2, name3, name4, name5, name6, name7, name8, name9)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting9)

        } else {remainmembers =
                setOf(name1, name2, name3, name4, name5, name6, name7, name8, name9,name10)
            pref.edit { putStringSet("remainmembers", remainmembers) }.apply { }
            findNavController().navigate(R.id.action_onlinefirstMeeting_to_onlineVoting10)
        }
    }


    override fun onPause() {

        super.onPause()

        soundPool.release()
    }


}
