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



        binding.seihekiup.setOnClickListener {
            var heki = binding.heki1.text
            var hekilength = heki.length
            var name = binding.getname.text
            var namelength = name.length

            if (hekilength== 0 && namelength == 0){
                binding.seihekiup.text = "お名前と性癖を教えてください"
            }
            else if (hekilength== 0 ){
                binding.seihekiup.text = "性癖を教えてください"
            }
            else if(namelength == 0){
                binding.seihekiup.text = "お名前を教えてください"

            }
            else if(hekilength >=1 && namelength >=1){
                onSeihekiUpTapped(it)
            }else{
                onSeihekiUpTapped(it)
            }
        }

    }
    fun onSeihekiUpTapped(view: View?){
        var intent = Intent(this, hekientory2::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putString("seiheki1",binding.heki1.text.toString())
            putString("name1",binding.getname.text.toString())
                .apply()
        }




    }
}