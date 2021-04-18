package com.hamu.seihekijinrou.MeetingandVotingandResult.ResultofVoting

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.EndofGame.End1ofTrue
import com.hamu.seihekijinrou.databinding.FragmentTrueresult2Binding

class trueresult2 : Fragment() {
    private var _binding: FragmentTrueresult2Binding? = null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrueresult2Binding.inflate(layoutInflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var ThistimeSuspect = pref.getString("ThistimeSuspect","")
        var jinrou = pref.getString("jinrou","")

        binding.textView9.text = "$ThistimeSuspect さんは性癖"

        binding.textView8.text = "「$jinrou」"



        loadingDelay()
        return binding.root

    }

    fun loadingDelay(){
        Handler().postDelayed(
            {
                val intent = Intent(context, End1ofTrue::class.java)
                startActivity(intent)
            },
            2000,
        )
    }
}




