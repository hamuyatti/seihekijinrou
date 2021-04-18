package com.hamu.seihekijinrou.MeetingandVotingandResult.ResultofVoting

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
import com.hamu.seihekijinrou.databinding.FragmentFalseresult2Binding

class falseresult2 : Fragment() {
    private var _binding: FragmentFalseresult2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFalseresult2Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)

        var LOSE = pref.getString("LOSE", "")
        var ThistimeSuspect = pref.getString("ThistimeSuspect","").toString()


        binding.check.text = "$ThistimeSuspect さんの性癖を公開しますか？"
        binding.Yes.setOnClickListener {
            findNavController().navigate(R.id.action_falseresult2_to_openseiheki)

        }
        binding.No.setOnClickListener {
            if(LOSE!!.isNotEmpty()){
                var intent = Intent(context, End1ofBad::class.java)
                startActivity(intent)}
            else{
                findNavController().navigate(R.id.action_falseresult2_to_meetingtime)
            }
        }
        return binding.root
    }

}

