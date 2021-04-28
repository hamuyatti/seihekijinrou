package com.hamu.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hamu.seihekijinrou.databinding.FragmentOnlinefirstMeetingBinding
import com.hamu.seihekijinrou.databinding.FragmentResultBinding


class Result : Fragment() {

    private var _binding: FragmentResultBinding?= null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }


}