package com.hamu.seihekijinrou

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.hamu.seihekijinrou.EndofGame.End2
import com.hamu.seihekijinrou.EndofGame.End3
import com.hamu.seihekijinrou.Start.gamestart
import com.hamu.seihekijinrou.databinding.FragmentOnlineVoting7Binding
import com.hamu.seihekijinrou.databinding.FragmentResultBinding
import com.hamu.seihekijinrou.databinding.FragmentTrueResultBinding


class trueResult : Fragment() {
    private var _binding: FragmentTrueResultBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTrueResultBinding.inflate(inflater, container, false)

        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var name1 = pref.getString("name1", "").toString()
        var name2 = pref.getString("name2", "").toString()
        var name3 = pref.getString("name3", "").toString()
        var name4 = pref.getString("name4", "").toString()
        var name5 = pref.getString("name5", "").toString()
        var name6 = pref.getString("name6", "").toString()

        binding.backtotitle.setOnClickListener {
            var intent = Intent(context, gamestart::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener {
            if ((name1.length > 0 || name2.length > 0 || name3.length > 0 || name4.length > 0 || name5.length > 0 )&& name6.length == 0) {
                var intent = Intent(context, End3::class.java)
                startActivity(intent)
            } else {
                var intent = Intent(context, End2::class.java)
                startActivity(intent)
            }
        }




        return binding.root
    }
}