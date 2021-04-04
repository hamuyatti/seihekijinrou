package com.example.seihekijinrou.Preparation.SeiHekientry

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ConfirmDialogs(private val message:String,private val okLabel:String,private val oKSelected:()->Unit,
                     private val cancelLabel:String, private val cancelSelected:()->Unit)
    :DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(message)
        builder.setPositiveButton(okLabel){
            dialog,which->oKSelected()
        }

        return builder.create()
    }







}