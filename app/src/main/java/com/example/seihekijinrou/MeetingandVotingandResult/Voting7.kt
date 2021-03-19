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
import com.example.seihekijinrou.databinding.FragmentVoting7Binding


class Voting7 : Fragment() {
    private lateinit var Suspect7: String
    private lateinit var remainmembers6: Set<String>
    private lateinit var members: MutableList<String>
    private lateinit var candidate1: String
    private lateinit var candidate2: String
    private lateinit var candidate3: String
    private lateinit var candidate4: String
    private lateinit var candidate5: String
    private lateinit var candidate6: String
    private lateinit var candidate7: String


    private var _binding: FragmentVoting7Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting7Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"

        var jinrouname = pref.getString("jinrouname", "")

        var fake = pref.getStringSet("remainmembers7", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]
            candidate7 = members[6]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6
            binding.name7.text = candidate7

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect7 = candidate1

                R.id.name2 -> Suspect7 = candidate2

                R.id.name3 -> Suspect7 = candidate3

                R.id.name4 -> Suspect7 = candidate4

                R.id.name5 -> Suspect7= candidate5

                R.id.name6 -> Suspect7 = candidate6

                R.id.name7 -> Suspect7 = candidate7



            }

            members.remove(Suspect7)
            remainmembers6 = members.toSet()

            binding.judge.setOnClickListener {
                pref.edit {
                    putStringSet("remainmembers6", remainmembers6)
                    putString("Suspect7", Suspect7)
                    putString("ThistimeSuspect",Suspect7)
                }.apply { }
                if (Suspect7 == jinrouname) {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting7_to_trueresult1)

                } else {
                    var pref = PreferenceManager.getDefaultSharedPreferences(context)

                    findNavController().navigate(R.id.action_voting7_to_falseresult1)
                }
            }

        }

        return binding.root
    }
}
