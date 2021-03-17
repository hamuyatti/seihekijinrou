package com.example.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.Preparation.numberofpeople
import com.example.seihekijinrou.R


class firstbetweenHekientry : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
       var pref =PreferenceManager.getDefaultSharedPreferences(context)
        var number = pref.getString("numberofpeople","")
        when(number){
            "３人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry3)
            "４人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry4)
            "５人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry5)
            "６人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry6)
            "７人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry7)
            "８人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry8)
            "９人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry9)
            "１０人"-> findNavController().navigate(R.id.action_firstbetweenHekientry_to_hekientry10)

        }
        return inflater.inflate(R.layout.fragment_firstbetween_hekientry, container, false)

    }


    }
