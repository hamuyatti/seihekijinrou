package com.example.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentHekientry5Binding



class Hekientry5 : Fragment() {
    private var _binding: FragmentHekientry5Binding? = null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHekientry5Binding.inflate(inflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var firstname = pref.getString("name10", "")
        var secondname = pref.getString("name9", "")
        var thirdname = pref.getString("name8", "")
        var forthname = pref.getString("name7", "")
        var fifthname = pref.getString("name6", "")
        var sixthname = pref.getString("name5", "")
        var seventhname = pref.getString("name4", "")
        var eighthname = pref.getString("name3", "")
        var ninethname = pref.getString("name2", "")

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/

            if (name == firstname || name == secondname || name == thirdname || name == forthname || name == fifthname || name == sixthname || name == seventhname
                || name == eighthname || name == ninethname) {
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
            putString("name5", binding.Heki.text.toString())
            putString("seiheki5", binding.Getname.text.toString())
                .apply()
            findNavController().navigate(R.id.action_hekientry5_to_hekientry4)
        }
    }
}
