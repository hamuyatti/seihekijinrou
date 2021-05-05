package com.hamu.seihekijinrou.Preparation.Online


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentSelectjinrouBinding


class selectjinrou : Fragment() {
    private var _binding: FragmentSelectjinrouBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSelectjinrouBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        var jinrouseiheki= pref.getString("jinrouseiheki","")

        Handler().postDelayed(
                            {
                                binding.jinrou.text = " $jinrouseiheki です。探し当てましょう！"

                                Handler().postDelayed(
                                        {
                                            findNavController().navigate(R.id.action_selectjinrou_to_onlinefirstMeeting)
                                        },
                                        1000,
                                )
                            },
                            3000,
                    )


                }
    }



