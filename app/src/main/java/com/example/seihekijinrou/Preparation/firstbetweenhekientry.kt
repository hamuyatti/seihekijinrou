package com.example.seihekijinrou.Preparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentHekientry9Binding

class firstbetweenhekientry : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var number = pref.getString("numberofpeople","")

          if(number == "３人"){
          findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry3)
          } else if(number == "４人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry4)
          }else if(number == "５人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry5)
          }else if(number == "６人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry6)
          }else if(number == "７人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry7)
          }else if(number == "８人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry8)
          }else if(number == "９人"){
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry9)
          }else{
              findNavController().navigate(R.id.action_firstbetweenhekientry_to_hekientry10)
          }





        return inflater.inflate(R.layout.fragment_firstbetweenhekientry, container, false)
    }


}