package com.example.seihekijinrou.Preparation.SeiHekientry

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.Preparation.Result1
import com.example.seihekijinrou.databinding.FragmentHekientry1Binding



class Hekientry1 :abstractHekientry() {
    private var _binding: FragmentHekientry1Binding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHekientry1Binding.inflate(inflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        name10 = pref.getString("name10", "").toString()
        name9 = pref.getString("name9", "").toString()
        name8 = pref.getString("name8", "").toString()
        name7 = pref.getString("name7", "").toString()
        name6 = pref.getString("name6", "").toString()
        name5 = pref.getString("name5", "").toString()
        name4 = pref.getString("name4", "").toString()
        name3 = pref.getString("name3", "").toString()
        name2 = pref.getString("name2", "").toString()

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/

            if ( name == name2 || name == name3 || name == name4 || name == name5 || name == name6 || name == name7
                || name == name8 || name == name9||name == name10)
                {
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

        override fun onSeihekiUpTapped() {
            var pref = PreferenceManager.getDefaultSharedPreferences(context)
            pref.edit {
                putString("name1", binding.Getname.text.toString()).apply()
                putString("seiheki1", binding.Heki.text.toString()).apply()
            }
            var intent = Intent(context, Result1::class.java)
            startActivity(intent)
        }
    }




