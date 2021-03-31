package com.example.seihekijinrou.Preparation.SeiHekientry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.R
import com.example.seihekijinrou.Start.seihekidata
import com.example.seihekijinrou.databinding.FragmentHekientry10Binding
import io.realm.Realm


class Hekientry10 :abstractHekientry() {
   private var _binding:FragmentHekientry10Binding? = null
    private val binding get()=_binding!!
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        realm = Realm.getDefaultInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentHekientry10Binding.inflate(inflater,container,false)

        binding.Seihekiup.setOnClickListener {
            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.Heki.text.toString()
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


                 realm.executeTransaction {
                     db:Realm->
                     var seiheki =seihekidata(heki)
                     realm.insert(seiheki)
                     realm.commitTransaction()

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



   override  fun onSeihekiUpTapped (){
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit {
            putString("name10", binding.Getname.text.toString()).apply()
            putString("seiheki10", binding.Heki.text.toString()).apply()
        }
        findNavController().navigate(R.id.action_hekientry10_to_hekientry9)
    }
}
abstract  class abstractHekientry: Fragment() {


    lateinit var name1:String
    lateinit var name2:String
    lateinit var name3:String
    lateinit var name4:String
    lateinit var name5:String
    lateinit var name6:String
    lateinit var name7:String
    lateinit var name8:String
    lateinit var name9:String
    lateinit var name10:String


    abstract  fun onSeihekiUpTapped()

    fun Snackbar(){
        val text = "追加しました"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()
    }
}



