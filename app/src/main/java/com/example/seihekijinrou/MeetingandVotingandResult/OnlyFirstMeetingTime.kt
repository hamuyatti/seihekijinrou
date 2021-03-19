package com.example.seihekijinrou.MeetingandVotingandResult

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
import com.example.seihekijinrou.databinding.FragmentOnlyFirstMeetingTimeBinding


class OnlyFirstMeetingTime : Fragment() {


    private var _binding: FragmentOnlyFirstMeetingTimeBinding? = null
    private val binding get() = _binding!!
    private lateinit var remainmembers3: Set<String>
    private lateinit var remainmembers4: Set<String>
    private lateinit var remainmembers5: Set<String>
    private lateinit var remainmembers6: Set<String>
    private lateinit var remainmembers7: Set<String>
    private lateinit var remainmembers8: Set<String>
    private lateinit var remainmembers9: Set<String>


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

        var jinrou = pref.getString("jinrou", "")
        binding.gametitle.text = "「$jinrou」"


        binding.timertext.text = "2:00"
        var timer = MyCountDownTimer(2 * 60 * 1000, 100)
        timer.start()

        var name1 = pref.getString("name1", "").toString()
        var name2 = pref.getString("name2", "").toString()
        var name3 = pref.getString("name3", "").toString()
        var name4 = pref.getString("name4", "").toString()
        var name5 = pref.getString("name5", "").toString()
        var name6 = pref.getString("name6", "").toString()
        var name7 = pref.getString("name7", "").toString()
        var name8 = pref.getString("name8", "").toString()
        var name9 = pref.getString("name9", "").toString()


        var numberofpeople = pref.getString("numberofpeople", "")

        binding.Meetingstop.setOnClickListener {
            if (numberofpeople == "３人") {
                remainmembers3 = setOf(name1, name2, name3)
                pref.edit { putStringSet("remainmembers3", remainmembers3) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting3)

            } else if (numberofpeople == "４人") {
                remainmembers4 = setOf(name1, name2, name3, name4)
                pref.edit { putStringSet("remainmembers4", remainmembers4) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting4)

            } else if (numberofpeople == "５人") {
                remainmembers5 = setOf(name1, name2, name3, name4, name5)
                pref.edit { putStringSet("remainmembers5", remainmembers5) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting5)

            } else if (numberofpeople == "６人") {
                remainmembers6 = setOf(name1, name2, name3, name4, name5, name6)
                pref.edit { putStringSet("remainmembers6", remainmembers6) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting6)

            } else if (numberofpeople == "７人") {
                remainmembers7 = setOf(name1, name2, name3, name4, name5, name6, name7)
                pref.edit { putStringSet("remainmembers7", remainmembers7) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting7)

            } else if (numberofpeople == "８人") {
                remainmembers8 = setOf(name1, name2, name3, name4, name5, name6, name7, name8)
                pref.edit { putStringSet("remainmembers8", remainmembers8) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting8)

            } else if (numberofpeople == "９人") {
                remainmembers9 =
                    setOf(name1, name2, name3, name4, name5, name6, name7, name8, name9)
                pref.edit { putStringSet("remainmembers9", remainmembers9) }.apply { }
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting9)

            } else {
                /*remainmembersは誰が残っているか特定するための配列なので、人数いっぱいの１０人の時は自明なので使いません*/
                findNavController().navigate(R.id.action_onlyFirstMeetingTime_to_voting10)
            }

        }

        return binding.root
    }

}
