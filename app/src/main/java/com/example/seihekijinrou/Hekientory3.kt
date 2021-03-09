package com.example.seihekijinrou

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientory3Binding


class hekientory3 : AppCompatActivity() {
    private lateinit var binding :ActivityHekientory3Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHekientory3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var firstname = pref.getString("name1","")
        var secondname = pref.getString("name2","")



        binding.seihekiup3.setOnClickListener {
            var heki = binding.heki3.text
            var hekilength = heki.length
            var name = binding.getname3.text.toString()
            var namelength = name.length

            if(name == firstname||name == secondname){
                binding.seihekiup3.text = "違う名前を使ってください"
            }
            else if (hekilength== 0 && namelength == 0){
                binding.seihekiup3.text = "お名前と性癖を教えてください"
            }
            else if (hekilength== 0 ){
                binding.seihekiup3.text = "性癖を教えてください"
            }
            else if(namelength == 0){
                binding.seihekiup3.text = "お名前を教えてください"

            }
            else if(hekilength >=1 && namelength >=1){
                onSeihekiUpTapped(it)
            }else{
                onSeihekiUpTapped(it)
            }
        }

    }


    fun onSeihekiUpTapped(view: View?){
        val intent = Intent(this, hekientory4::class.java)
        startActivity(intent)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putString("seiheki3",binding.heki3.text.toString())
            putString("name3",binding.getname3.text.toString())
                    .apply()
        }
    }

}