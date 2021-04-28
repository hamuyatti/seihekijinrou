package com.hamu.seihekijinrou

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import com.hamu.seihekijinrou.Start.explanation
import com.hamu.seihekijinrou.databinding.FragmentEqualvoteBinding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting10Binding
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting9Binding


class Equalvote : Fragment() {
    private var _binding: FragmentEqualvoteBinding?= null
    private val binding get() = _binding!!
    var wheretogo:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEqualvoteBinding.inflate(inflater, container, false)
        wheretogo = (arguments?.get("where") as Int)

        Handler().postDelayed(
            {
              Wheretogo()
            },
            1000,
        )
        return binding.root
    }

    fun Wheretogo(){
        when(wheretogo){
            3->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting3)
            4->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting4)
            5->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting5)
            6->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting6)
            7->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting7)
            8->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting8)
            9->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting9)
            10->findNavController().navigate(R.id.action_equalvote2_to_onlineVoting10)

        }

    }


}


