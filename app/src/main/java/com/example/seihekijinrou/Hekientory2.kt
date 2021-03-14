package com.example.seihekijinrou

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientory2Binding

class hekientory2 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientory2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientory2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var firstname =  pref.getString("name10","")
        var secondname =  pref.getString("name9","")
        var thirdname = pref.getString("name8","")
        var forthname = pref.getString("name7","")
        var fifthname = pref.getString("name6","")
        var sixthname = pref.getString("name5","")
        var seventhname =pref.getString("name4","")
        var eighthname =pref.getString("name3","")
        binding.seihekiup2.setOnClickListener {

            /*以下の変数定義はボタンを押してからの処理にしないとずっとnull,0文字になっちゃうよ！(自分用)*/
            var heki = binding.heki.text
            var hekilength = heki.length
            var name = binding.getname.text.toString()
            var namelength = name.length

            /*バリデーションは性癖については行いません。かぶり表示がでてしまうと前エントリー者の入力内容が予測できてしまうため。*/
            if(name==firstname ||name==secondname ||name==thirdname ||name ==forthname ||name ==fifthname ||name ==sixthname||name==seventhname
                ||name ==eighthname) {
                binding.seihekiup2.text ="違う名前を使ってください"
            }
            else if (hekilength== 0 && namelength == 0){
                binding.seihekiup2.text = "お名前と性癖を教えてください"
            }
            else if (hekilength== 0 ){
                binding.seihekiup2.text = "性癖を教えてください"
            }
            else if(namelength == 0){
                binding.seihekiup2.text = "お名前を教えてください"

            }
            else {
                onSeihekiUpTapped(it)
            }
        }

    }
    fun onSeihekiUpTapped(view: View?){
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putString("seiheki2",binding.heki.text.toString())
            putString("name2",binding.getname.text.toString())
                    .apply()
        }

    }
}