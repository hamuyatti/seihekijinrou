package com.example.seihekijinrou.Preparation.SeiHekientry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.Start.seihekidata
import com.example.seihekijinrou.databinding.FragmentHekientry7Binding
import io.realm.Realm
import io.realm.kotlin.createObject

class Hekientry7 : abstractHekientry() {

    private var _binding: FragmentHekientry7Binding? = null
    private val binding get()=_binding!!
    private lateinit var realm: Realm
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHekientry7Binding.inflate(inflater,container,false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        name10 = pref.getString("name10", "").toString()
        name9 = pref.getString("name9", "").toString()
        name8 = pref.getString("name8", "").toString()

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text.toString()
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/

            if (name == name10 ||name == name9|| name == name8) {
                binding.Seihekiup.text = "違う名前を使ってください"
            } else if (hekilength == 0 && namelength == 0) {
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.Seihekiup.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.Seihekiup.text = "お名前を教えてください"

            } else {
                realm.executeTransaction {
                        db: Realm ->
                    var Seihekidata = db.createObject<seihekidata>()
                    Seihekidata.seiheki = heki
                }

                Snackbar()
                onSeihekiUpTapped()

            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

   override fun onSeihekiUpTapped() {
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putString("name7", binding.Getname.text.toString()).apply()
            putString("seiheki7", binding.Heki.text.toString()).apply()
        }
        findNavController().navigate(R.id.action_hekientry7_to_hekientry6)
    }
}
