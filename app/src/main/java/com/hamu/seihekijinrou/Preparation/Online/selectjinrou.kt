package com.hamu.seihekijinrou.Preparation.Online


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hamu.seihekijinrou.R
import com.hamu.seihekijinrou.databinding.FragmentSelectjinrouBinding


class selectjinrou : Fragment() {
    private var _binding: FragmentSelectjinrouBinding? = null
    private val binding get() = _binding!!
    var db = Firebase.firestore
    lateinit var tmp:String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSelectjinrouBinding.inflate(layoutInflater, container, false)
        var pref = PreferenceManager.getDefaultSharedPreferences(context)
        var roomname = pref.getString("roomname", "")
        var collection = db.collection("$roomname")

        collection.document("性癖情報")
                .get()
                .addOnSuccessListener {
                    var heki1 = it.data?.get("性癖1")
                    var heki2 = it.data?.get("性癖2")
                    var heki3 = it.data?.get("性癖3")
                    var heki4 = it.data?.get("性癖4")
                    var heki5 = it.data?.get("性癖5")
                    var heki6 = it.data?.get("性癖6")
                    var heki7 = it.data?.get("性癖7")
                    var heki8 = it.data?.get("性癖8")
                    var heki9 = it.data?.get("性癖9")
                    var heki10 = it.data?.get("性癖10")

                    binding.jinrou.text = "${heki6}"


                    pref.edit {
                        putString("seiheki1", heki1 as String?);putString("seiheki2", heki2 as String?)
                        putString("seiheki3", heki3 as String?);putString("seiheki4", heki4 as String?)
                        putString("seiheki5", heki5 as String?);putString("seiheki6", heki6 as String?)
                        putString("seiheki7", heki7 as String?);putString("seiheki8", heki8 as String?)
                        putString("seiheki9", heki9 as String?);putString("seiheki10", heki10 as String?)
                        putString("jinrouseiheki",heki1 )

                    }.apply {  }
                }
        collection.document("名前情報")
                .get()
                .addOnSuccessListener {
                    var name1 = it.data?.get("名前1")
                    var name2 = it.data?.get("名前2")
                    var name3 = it.data?.get("名前3")
                    var name4 = it.data?.get("名前4")
                    var name5 = it.data?.get("名前5")
                    var name6 = it.data?.get("名前6")
                    var name7 = it.data?.get("名前7")
                    var name8 = it.data?.get("名前8")
                    var name9 = it.data?.get("名前9")
                    var name10 = it.data?.get("名前10")

                    binding.textView5.text = "${name6}"

                    pref.edit {
                        putString("name1", name1 as String?);putString("name2", name2 as String?)
                        putString("name3", name3 as String?);putString("name4", name4 as String?)
                        putString("name5", name5 as String?);putString("name6", name6 as String?)
                        putString("name7", name7 as String?);putString("name8", name8 as String?)
                        putString("name9", name9 as String?);putString("name10", name10 as String?)
                        putString("jinrou",name1 )
                    }.apply {  }
                }
        binding.stop.setOnClickListener {
            tmp = "押されました"
            findNavController().navigate(R.id.action_selectjinrou_to_onlinefirstMeeting)
        }

        return binding.root
    }
}

