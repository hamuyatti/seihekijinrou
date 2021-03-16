package com.example.seihekijinrou.Preparation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientry5Binding

class Hekientry5 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientry5Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientry5Binding.inflate(layoutInflater)
        setContentView(binding.root)


        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var firstname =  pref.getString("name10","")
        var secondname =  pref.getString("name9","")
        var thirdname = pref.getString("name8","")
        var forthname = pref.getString("name7","")
        var fifthname = pref.getString("name6","")

        binding.Seihekiup.setOnClickListener {
            var heki = binding.Heki.text
            var hekilength = heki.length
            var name = binding.Getname.text.toString()
            var namelength = name.length

            if(name==firstname ||name==secondname ||name==thirdname ||name ==forthname ||name ==fifthname){
                binding.Seihekiup.text = "違う名前を使ってください"
            }
            else if (hekilength == 0 && namelength == 0) {
                binding.Seihekiup.text = "お名前と性癖を教えてください"
            } else if (hekilength == 0) {
                binding.Seihekiup.text = "性癖を教えてください"
            } else if (namelength == 0) {
                binding.Seihekiup.text = "お名前を教えてください"

            }  else {
                onSeihekiUpTapped(it)
            }
        }

    }

    fun onSeihekiUpTapped(view: View?) {
        var intent = Intent(this, Hekientry4::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putString("seiheki5", binding.Heki.text.toString())
            putString("name5", binding.Getname.text.toString())
                .apply()
        }
    }
}