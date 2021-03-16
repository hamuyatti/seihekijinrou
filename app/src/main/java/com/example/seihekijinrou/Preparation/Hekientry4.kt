package com.example.seihekijinrou.Preparation


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityHekientory4Binding

class Hekientry4 : AppCompatActivity() {
    private lateinit var binding: ActivityHekientory4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHekientory4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var firstname =  pref.getString("name10","")
        var secondname =  pref.getString("name9","")
        var thirdname = pref.getString("name8","")
        var forthname = pref.getString("name7","")
        var fifthname = pref.getString("name6","")
        var sixthname = pref.getString("name5","")

        binding.seihekiup4.setOnClickListener {
            var heki = binding.heki.text
            var hekilength = heki.length
            var name = binding.getname.text.toString()
            var namelength = name.length

            if(name==firstname ||name==secondname ||name==thirdname ||name ==forthname ||name ==fifthname ||name ==sixthname){
                binding.seihekiup4.text = "違う名前を使ってください"
            }
            else if (hekilength== 0 && namelength == 0){
                binding.seihekiup4.text = "お名前と性癖を教えてください"
            }
            else if (hekilength== 0 ){
                binding.seihekiup4.text = "性癖を教えてください"
            }
            else if(namelength == 0){
                binding.seihekiup4.text = "お名前を教えてください"

            }
            else if(hekilength >=1 && namelength >=1){
                onSeihekiUpTapped(it)
            }else{
                onSeihekiUpTapped(it)
            }
        }


    }

    fun onSeihekiUpTapped(view: View?) {
        var intent = Intent(this, Hekientry3::class.java)
        startActivity(intent)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putString("seiheki4", binding.heki.text.toString())
            putString("name4", binding.getname.text.toString())
                    .apply()
        }

    }
}

