package com.hamu.seihekijinrou

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting3Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting4Binding
import com.hamu.seihekijinrou.databinding.FragmentWhenOpinionsAreUitedBinding

class WhenOpinionsAreUited : Fragment() {
    private var _binding: FragmentWhenOpinionsAreUitedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentWhenOpinionsAreUitedBinding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrou = pref.getString("jinrou","")
        var jinrouseiheki = pref.getString("jinrouseiheki","")

        var Suspect = arguments?.get("Suspect")as String?

        if(Suspect==jinrou){
            binding.text.text = "$Suspect さんは、、"
            Handler().postDelayed(
                {
                    binding.text.text = "性癖「{$jinrouseiheki}」の持ち主です！"
                    Handler().postDelayed(
                        {
                            findNavController().navigate(R.id.action_whenOpinionsAreUited_to_trueResult)
                        },
                        2000,
                    )
                },
                2000,
            )

        }else{
            binding.text.text = "$Suspect さんは、、"


            Handler().postDelayed(
                    {
                        binding.text.text = "人狼ではありません！"
                        Handler().postDelayed(
                            {
                                findNavController().navigate(R.id.action_whenOpinionsAreUited_to_onlineMeetingtime)
                            },
                            2000,
                        )
                    },
                            2000,
            )


        }
        return binding.root
    }


}