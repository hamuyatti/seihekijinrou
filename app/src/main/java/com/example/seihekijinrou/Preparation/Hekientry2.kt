package com.example.seihekijinrou.Preparation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentHekientry10Binding
import com.example.seihekijinrou.databinding.FragmentHekientry2Binding
import com.example.seihekijinrou.databinding.FragmentHekientry4Binding
import com.example.seihekijinrou.databinding.FragmentHekientry9Binding


class Hekientry2 : Fragment() {
    private var _binding: FragmentHekientry2Binding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHekientry2Binding.inflate(inflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var name10 = pref.getString("name10", "")
        var name9 = pref.getString("name9", "")
        var name8 = pref.getString("name8", "")
        var name7 = pref.getString("name7", "")
        var name6 = pref.getString("name6", "")
        var name5 = pref.getString("name5", "")
        var name4 = pref.getString("name4", "")
        var name3 = pref.getString("name3", "")

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/

            if ( name == name3 || name == name4 || name == name5 || name == name6 || name == name7
                || name == name8 || name == name9||name == name10) {
                binding.Seihekiup.text = "違う名前を使ってください"
            } else if (hekilength == 0 && namelength == 0) {
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.Seihekiup.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.Seihekiup.text = "お名前を教えてください"

            } else {
                onSeihekiUpTapped()

            }
        }
        return binding.root
    }

    fun onSeihekiUpTapped() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putString("name2", binding.Getname.text.toString()).apply()
            putString("seiheki2", binding.Heki.text.toString()).apply()
        }
        findNavController().navigate(R.id.action_hekientry2_to_hekientry1)
    }
}
