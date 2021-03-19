package com.example.seihekijinrou

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.databinding.FragmentHekientry10Binding


class Hekientry10 : Fragment() {
   private var _binding:FragmentHekientry10Binding? = null
    private val binding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentHekientry10Binding.inflate(inflater,container,false)

        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var name10 = pref.getString("name10", "")
        var name9 = pref.getString("name9", "")
        var name8 = pref.getString("name8", "")
        var name7 = pref.getString("name7", "")
        var name6 = pref.getString("name6", "")
        var name5 = pref.getString("name5", "")
        var name4 = pref.getString("name4", "")
        var name3 = pref.getString("name3", "")
        var name2 = pref.getString("name2", "")

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/

             if (hekilength == 0 && namelength == 0) {
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

    fun onSeihekiUpTapped (){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putString("name10", binding.Heki.text.toString()).apply()
            putString("seiheki10", binding.Getname.text.toString()).apply()
        }
        findNavController().navigate(R.id.action_hekientry10_to_hekientry9)
    }
}





