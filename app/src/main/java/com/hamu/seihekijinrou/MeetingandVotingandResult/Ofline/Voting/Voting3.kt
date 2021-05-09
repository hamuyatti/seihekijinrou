package com.hamu.seihekijinrou.MeetingandVotingandResult.Ofline.Voting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.databinding.FragmentVoting3Binding
import com.hamu.seihekijinrou.R


class Voting3 : abstractVoting() {
    private lateinit var Suspect3: String
    private lateinit var members: MutableList<String>

    private var _binding: FragmentVoting3Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting3Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)

        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var fake = pref.getStringSet("remainmembers3", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect3 = candidate1

                R.id.name2 -> Suspect3 = candidate2

                R.id.name3 -> Suspect3 = candidate3


            }
            binding.voting.setOnClickListener {
                members.remove(Suspect3)
                judge()
            }

        }


        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putString("Suspect3",Suspect3)
            putString("ThistimeSuspect",Suspect3)
        }.apply {  }
        if (Suspect3 == jinrouname) {

            findNavController().navigate(R.id.action_voting3_to_trueresult1)


        } else {
            pref.edit {
                putString("LOSE","負けた時の判別用です。")
            }.apply {  }

            findNavController().navigate(R.id.action_voting3_to_falseresult1)

        }
    }
}

