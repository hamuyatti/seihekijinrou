package com.example.seihekijinrou.MeetingandVotingandResult.Meeting

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentMeetingtimeBinding


class Meetingtime : Fragment() {
    private var _binding:FragmentMeetingtimeBinding?=null
    private val binding get() = _binding!!

    inner class MyCountDownTimer(millsInfuture: Long, countDownInterval: Long) :
        CountDownTimer(millsInfuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            binding.timertext.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            binding.timertext.text = "0:00"
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentMeetingtimeBinding.inflate(layoutInflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit{
            remove("ThistimeSuspect").commit()
        }
        var jinrou = pref.getString("jinrou", "")
        binding.gametitle.text =  "「$jinrou」"
        binding.timertext.text = "2:00"
        var timer = MyCountDownTimer(2*60*1000,100)
        timer.start()


        var Suspect9 =  pref.getString("Suspect9","") as String
        var Suspect8 =  pref.getString("Suspect8","") as String
        var Suspect7 =  pref.getString("Suspect7","") as String
        var Suspect6 =  pref.getString("Suspect6","") as String
        var Suspect5 =  pref.getString("Suspect5","") as String
        var Suspect4 =  pref.getString("Suspect4","") as String
        var Suspect3 =  pref.getString("Suspect3","") as String


        binding.Meetingstop.setOnClickListener {
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
return binding.root
    }

}