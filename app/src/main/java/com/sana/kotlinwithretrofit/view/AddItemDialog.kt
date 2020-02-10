package com.sana.kotlinwithretrofit.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sana.kotlinwithretrofit.R
import com.sana.kotlinwithretrofit.User

class AddItemDialog(context: Context) : Dialog(context){

    lateinit var et_name: EditText
    lateinit var et_userType: EditText
    lateinit var btn_add: Button
    var iAddItemCallback: IAddItemCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item_dialog)
        /* To display full Screen of Dialog */
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        init()
    }

    fun init(){
        et_name = findViewById(R.id.et_name)
        et_userType = findViewById(R.id.et_userType)
        btn_add = findViewById(R.id.btn_add)

        btn_add.setOnClickListener(View.OnClickListener {
            dismiss()
            if(iAddItemCallback != null){
                iAddItemCallback!!.addItem(User(et_name.text.toString(),et_userType.text.toString(),""))
            }
        })
    }

    interface IAddItemCallback{
        fun addItem(user: User)
    }

    fun onAddItem(iAddItemCallback: IAddItemCallback){
        this.iAddItemCallback = iAddItemCallback
    }
}
