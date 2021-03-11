package com.example.seihekijinrou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.example.seihekijinrou.databinding.ActivityNumberofpeopleBinding

class numberofpeople : AppCompatActivity() {
    private lateinit var binding: ActivityNumberofpeopleBinding
    private lateinit var item: String
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNumberofpeopleBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                var spinner = parent as? Spinner
                item = (spinner?.selectedItem as? String).toString()
                item?.let {
                    if (it.isNotEmpty()) {
                        binding.confirm.text = "$it でよろしいですか？"
                    }
                }

            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                item = "３人"

            }


        }




        binding.gamestart.setOnClickListener {
            putnumber()
            if (item == "３人") {
                var intent = Intent(this, hekientory3::class.java)
                startActivity(intent)

            } else if (item == "４人") {
                var intent = Intent(this, hekientory4::class.java)
                startActivity(intent)

            } else if (item == "５人") {
                var intent = Intent(this, Hekientry5::class.java)
                startActivity(intent)

            } else if (item == "６人") {
                var intent = Intent(this, Hekientry6::class.java)
                startActivity(intent)

            } else if (item == "７人") {
                var intent = Intent(this, Hekientry7::class.java)
                startActivity(intent)

            } else if (item == "８人") {
                var intent = Intent(this, Hekientry8::class.java)
                startActivity(intent)

            } else if (item == "９人") {
                var intent = Intent(this, Hekientry9::class.java)
                startActivity(intent)

            } else {
                var intent = Intent(this, Hekientry10::class.java)
                startActivity(intent)


            }
        }


        }
    fun putnumber(){
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit {
            putString("numberofpeople", item)
                    .apply()
    }

    }
}





