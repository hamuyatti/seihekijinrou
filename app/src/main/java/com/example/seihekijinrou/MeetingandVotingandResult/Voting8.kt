package com.example.seihekijinrou.MeetingandVotingandResult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentVoting8Binding


class Voting8 : Fragment() {
    private lateinit var Suspect8: String
    private lateinit var remainmembers7: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String
    private lateinit var candidate5: String
    private lateinit var candidate6: String
    private lateinit var candidate7: String
    private lateinit var candidate8: String

    private var _binding: FragmentVoting8Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting8Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")

        var fake = pref.getStringSet("remainmembers8", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]
            candidate7 = members[6]
            candidate8 = members[7]


            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7
            binding.name8.text = candidate8

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect8 = candidate1

                R.id.name2 -> Suspect8 = candidate2

                R.id.name3 -> Suspect8 = candidate3

                R.id.name4 -> Suspect8 = candidate4

                R.id.name5 -> Suspect8= candidate5

                R.id.name6 -> Suspect8 = candidate6

                R.id.name7 -> Suspect8 = candidate7

                R.id.name8 -> Suspect8 = candidate8


            }

            members.remove(Suspect8)
            remainmembers7 = members.toSet()

            binding.judge.setOnClickListener {
                pref.edit {
                    putStringSet("remainmembers7", remainmembers7)
                    putString("Suspect8", Suspect8)
                    putString("ThistimeSuspect",Suspect8)
                }.apply { }
                if (Suspect8 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting8_to_trueresult1)

                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting8_to_falseresult1)
                }
            }

        }

        return binding.root
    }
}
