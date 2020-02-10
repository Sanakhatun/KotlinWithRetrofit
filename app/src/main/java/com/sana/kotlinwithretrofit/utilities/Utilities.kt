package com.sana.kotlinwithretrofit.utilities

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.sana.kotlinwithretrofit.R

object Utilities {

    fun showAlert(context: Context, message: String) {
        var dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.layout_custom_dialog)
        val body = dialog.findViewById<TextView>(R.id.tv_message)
        body.text = message
        val btn_ok = dialog.findViewById<Button>(R.id.btn_ok)
        btn_ok.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}