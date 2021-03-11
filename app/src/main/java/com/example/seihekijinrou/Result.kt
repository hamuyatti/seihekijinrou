package com.example.seihekijinrou

import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.Settings.Global.putString
import android.view.View
import androidx.core.content.edit
import com.example.seihekijinrou.databinding.ActivityResultBinding
import androidx.preference.PreferenceManager

class Result : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var pref = PreferenceManager.getDefaultSharedPreferences(this)
        var numberofpeople = pref.getString("numberofpeople","")


        var seiheki1 = pref.getString("seiheki1", "")
        var seiheki2 = pref.getString("seiheki2", "")
        var seiheki3 = pref.getString("seiheki3", "")
        var seiheki4 = pref.getString("seiheki4", "")
        var seiheki5 = pref.getString("seiheki5", "")
        var seiheki6 = pref.getString("seiheki6", "")
        var seiheki7 = pref.getString("seiheki7", "")
        var seiheki8 = pref.getString("seiheki8", "")
        var seiheki9 = pref.getString("seiheki9", "")
        var seiheki10 = pref.getString("seiheki10", "")

        var name1 = pref.getString("name1", "")
        var name2 = pref.getString("name2", "")
        var name3 = pref.getString("name3", "")
        var name4 = pref.getString("name4", "")
        var name5 = pref.getString("name5", "")
        var name6 = pref.getString("name6", "")
        var name7 = pref.getString("name7", "")
        var name8 = pref.getString("name8", "")
        var name9 = pref.getString("name9", "")
        var name10 = pref.getString("name10", "")

        if(numberofpeople =="１０人") {

            var number = (1..10).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else if (number == 4) {
                seiheki4
            } else if (number == 5) {
                seiheki5
            } else if (number == 6) {
                seiheki6
            } else if (number == 7) {
                seiheki7
            } else if (number == 8) {
                seiheki8
            } else if (number == 9) {
                seiheki9
            } else {
                seiheki10

            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else if (jinrou == seiheki4) {
                name4
            } else if (jinrou == seiheki5) {
                name5
            } else if (jinrou == seiheki6) {
                name6
            } else if (jinrou == seiheki7) {
                name7
            } else if (jinrou == seiheki8) {
                name8
            } else if (jinrou == seiheki9) {
                name9
            } else {
                name10
            }

            pref.edit {
                putString("jinrou", jinrou)
                putString("jinrouname", jinrouname)

        }
        }
            else if(numberofpeople =="９人") {

            var number = (1..9).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else if (number == 4) {
                seiheki4
            } else if (number == 5) {
                seiheki5
            } else if (number == 6) {
                seiheki6
            } else if (number == 7) {
                seiheki7
            } else if (number == 8) {
                seiheki8
            } else  {
                seiheki9
            }



            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else if (jinrou == seiheki4) {
                name4
            } else if (jinrou == seiheki5) {
                name5
            } else if (jinrou == seiheki6) {
                name6
            } else if (jinrou == seiheki7) {
                name7
            } else if (jinrou == seiheki8) {
                name8
            } else {
                name9
            }

            pref.edit {
                putString("jinrou", jinrou)
            }
            pref.edit {
                putString("jinrouname", jinrouname)


            }
        }
               else  if(numberofpeople =="８人") {

                    var number = (1..8).random()

                    var jinrou = if (number == 1) {
                        seiheki1
                    } else if (number == 2) {
                        seiheki2
                    } else if (number == 3) {
                        seiheki3
                    } else if (number == 4) {
                        seiheki4
                    } else if (number == 5) {
                        seiheki5
                    } else if (number == 6) {
                        seiheki6
                    } else if (number == 7) {
                        seiheki7
                    } else {
                        seiheki8
                    }


                    var jinrouname = if (jinrou == seiheki1) {
                        name1
                    } else if (jinrou == seiheki2) {
                        name2
                    } else if (jinrou == seiheki3) {
                        name3
                    } else if (jinrou == seiheki4) {
                        name4
                    } else if (jinrou == seiheki5) {
                        name5
                    } else if (jinrou == seiheki6) {
                        name6
                    } else if (jinrou == seiheki7) {
                        name7
                    } else {
                        name8
                    }

                    pref.edit {
                        putString("jinrou", jinrou)
                    }
                    pref.edit {
                        putString("jinrouname", jinrouname)


                    }
        } else  if(numberofpeople =="７人") {

            var number = (1..7).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else if (number == 4) {
                seiheki4
            } else if (number == 5) {
                seiheki5
            } else if (number == 6) {
                seiheki6
            } else  {
                seiheki7
            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else if (jinrou == seiheki4) {
                name4
            } else if (jinrou == seiheki5) {
                name5
            } else if (jinrou == seiheki6) {
                name6
            } else {
                name7
            }

            pref.edit {
                putString("jinrou", jinrou)
            }
            pref.edit {
                putString("jinrouname", jinrouname)


            }
        } else  if(numberofpeople =="６人") {

            var number = (1..6).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else if (number == 4) {
                seiheki4
            } else if (number == 5) {
                seiheki5
            } else  {
                seiheki6
            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else if (jinrou == seiheki4) {
                name4
            } else if (jinrou == seiheki5) {
                name5
            } else  {
                name6
            }

            pref.edit {
                putString("jinrou", jinrou)
            }
            pref.edit {
                putString("jinrouname", jinrouname)


            }
        } else  if(numberofpeople =="５人") {

            var number = (1..5).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else if (number == 4) {
                seiheki4
            } else  {
                seiheki5
            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else if (jinrou == seiheki4) {
                name4
            } else {
                name5
            }

            pref.edit {
                putString("jinrou", jinrou)
            }
            pref.edit {
                putString("jinrouname", jinrouname)


            }
        } else  if(numberofpeople =="４人") {

            var number = (1..4).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else if (number == 3) {
                seiheki3
            } else  {
                seiheki4
            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else if (jinrou == seiheki3) {
                name3
            } else  {
                name4
            }

            pref.edit {
                putString("jinrou", jinrou)
            }
            pref.edit {
                putString("jinrouname", jinrouname)


            }
        } else  {

            var number = (1..3).random()

            var jinrou = if (number == 1) {
                seiheki1
            } else if (number == 2) {
                seiheki2
            } else{
                seiheki3
            }


            var jinrouname = if (jinrou == seiheki1) {
                name1
            } else if (jinrou == seiheki2) {
                name2
            } else  {
                name3
            }

            pref.edit {
                putString("jinrou", jinrou)

                putString("jinrouname", jinrouname)


            }.apply {  }
        }


        loadingDelay()

    }
    fun loadingDelay(){
        Handler().postDelayed(
            {
                val intent = Intent(this, Result2::class.java)
                startActivity(intent)
            },
            1000,
        )
    }
}

