package com.example.seihekijinrou.Start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seihekijinrou.databinding.FragmentCheckrulesImegesBinding


private const val  IMG_RED_ID= "IMG_RED_ID"

class checkrulesImeges : Fragment() {

    private var imageResId:Int? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
           imageResId = it.getInt(IMG_RED_ID)
        }
    }

    private  var _binding:FragmentCheckrulesImegesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckrulesImegesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object {
        @JvmStatic
        fun newInstance(imageResId:Int) =
            checkrulesImeges().apply {
                arguments = Bundle().apply {
                    imageResId?.let { putInt(IMG_RED_ID, it) }
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageResId?.let{
            binding.imageView.setImageResource(it)
        }
    }
}