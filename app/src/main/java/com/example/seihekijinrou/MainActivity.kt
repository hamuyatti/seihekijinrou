package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        var ninthname = pref.getString("name2","")


        binding.Seihekiup.setOnClickListener {
            var heki = binding.heki1.text
            var hekilength = heki.length
            var name = binding.getname.text.toString()
            var namelength = name.length

            if((name==firstname ||name==secondname ||name==thirdname ||name ==forthname ||name ==fifthname ||name ==sixthname||name==seventhname
                        ||name ==eighthname ||name ==ninthname)){
                binding.Seihekiup.text = "違う名前を使ってください"
            }            else if (hekilength== 0 && namelength == 0){
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            }
            else if (hekilength== 0 ){
                binding.Seihekiup.text = "性癖を教えてください"
            }
            else if(namelength == 0){
                binding.Seihekiup.text = "お名前を教えてください"

            }
            else {
                onSeihekiUpTapped(it)

            }
        }

    }
    fun onSeihekiUpTapped(view: View?){
        var intent = Intent(this, Result::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putString("seiheki1",binding.heki1.text.toString())
            putString("name1",binding.getname.text.toString())
                .apply()
        }




    }
}