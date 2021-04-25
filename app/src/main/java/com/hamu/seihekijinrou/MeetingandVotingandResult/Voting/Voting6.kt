package com.hamu.seihekijinrou.MeetingandVotingandResult.Voting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.R

import com.hamu.seihekijinrou.databinding.FragmentVoting6Binding


class Voting6 : abstractVoting() {
    private lateinit var Suspect6: String
    private lateinit var remainmembers5: Set<String>
    private lateinit var members: MutableList<String>
    private var _binding: FragmentVoting6Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoting6Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou", "")
        binding.jinrouseiheki.text = "$jinrou は誰の性癖？？"


        var fake = pref.getStringSet("remainmembers6", setOf(""))
        if (fake != null) {
            members = fake.toMutableList()
            candidate1 = members[0]
            candidate2 = members[1]
            candidate3 = members[2]
            candidate4 = members[3]
            candidate5 = members[4]
            candidate6 = members[5]

            binding.name1.text = candidate1
            binding.name2.text = candidate2
            binding.name3.text = candidate3
            binding.name4.text = candidate4
            binding.name5.text = candidate5
            binding.name6.text = candidate6

        }
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.name1 -> Suspect6 = candidate1

                R.id.name2 -> Suspect6 = candidate2

                R.id.name3 -> Suspect6 = candidate3

                R.id.name4 -> Suspect6 = candidate4

                R.id.name5 -> Suspect6= candidate5

                R.id.name6 -> Suspect6 = candidate6

            }
            binding.voting.setOnClickListener {
                members.remove(Suspect6)
                remainmembers5 = members.toSet()
                judge()
            }



        }

        return binding.root
    }

    override fun judge() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouname = pref.getString("jinrouname", "")
        pref.edit {
            putStringSet("remainmembers5", remainmembers5)
            putString("Suspect6", Suspect6)
            putString("ThistimeSuspect",Suspect6)
        }.apply { }
        if (Suspect6 == jinrouname) {

            findNavController().navigate(R.id.action_voting6_to_trueresult1)

        } else {

            findNavController().navigate(R.id.action_voting6_to_falseresult1)
        }
    }
}
