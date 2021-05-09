package com.hamu.seihekijinrou.MeetingandVotingandResult.Ofline.ResultofVoting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.EndofGame.End1ofBad
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentOpenseihekiBinding


class openseiheki : Fragment() {
    private var _binding: FragmentOpenseihekiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOpenseihekiBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var ThistimeSuspect = pref.getString("ThistimeSuspect", "")
        var LOSE = pref.getString("LOSE", "")

        var candidate1 = pref.getString("name1", "")
        var candidate2 = pref.getString("name2", "")
        var candidate3 = pref.getString("name3", "")
        var candidate4 = pref.getString("name4", "")
        var candidate5 = pref.getString("name5", "")
        var candidate6 = pref.getString("name6", "")
        var candidate7 = pref.getString("name7", "")
        var candidate8 = pref.getString("name8", "")
        var candidate9 = pref.getString("name9", "")

        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")
        var seiheki5 = pref.getString("seiheki5", "")
        var seiheki6 = pref.getString("seiheki6", "")
        var seiheki7 = pref.getString("seiheki7", "")
        var seiheki8 = pref.getString("seiheki8", "")
        var seiheki9 = pref.getString("seiheki9", "")
        var seiheki10 = pref.getString("seiheki10", "")

        var Suspecterseiheki = if (ThistimeSuspect == candidate1) {
            seiheki1
        } else if (ThistimeSuspect == candidate2) {
            seiheki2
        } else if (ThistimeSuspect == candidate3) {
            seiheki3
        } else if (ThistimeSuspect == candidate4) {
            seiheki4
        } else if (ThistimeSuspect == candidate5) {
            seiheki5
        } else if (ThistimeSuspect == candidate6) {
            seiheki6
        } else if (ThistimeSuspect == candidate7) {
            seiheki7
        } else if (ThistimeSuspect == candidate8) {
            seiheki8
        } else if (ThistimeSuspect == candidate9) {
            seiheki9
        } else {
            seiheki10
        }
        binding.Suspectername1.text = "$ThistimeSuspect さんの性癖は${Suspecterseiheki}でした。"

        binding.button2.setOnClickListener {
            if (LOSE != null) {
                if (LOSE.isNotEmpty() == true) {
                    val intent = Intent(context, End1ofBad::class.java)
                    startActivity(intent)
                } else {
                    findNavController().navigate(R.id.action_openseiheki_to_meetingtime)
                }
            }
        }
        return binding.root
    }
}