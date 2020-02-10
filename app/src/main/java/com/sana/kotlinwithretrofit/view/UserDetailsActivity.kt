package com.sana.kotlinwithretrofit

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sana.kotlinwithretrofit.common.BaseActivity
import com.sana.kotlinwithretrofit.view.IDeleteItemCallback
import java.lang.Exception

class UserDetailsActivity : BaseActivity() {

    lateinit var image: ImageView
    //var iDeleteItemCallback: IDeleteItemCallback? = null
    var selectedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        initialise()
    }

    private fun initialise(){
        super.init()
        image = findViewById(R.id.image)
        try{

            var intent = getIntent()

            if(intent != null){
                if(intent.hasExtra("selectedPosition")){
                    selectedPosition = intent.getIntExtra("selectedPosition", 0)
                }

                if(intent.hasExtra("image") && !intent.getStringExtra("image").equals("")){

                    Glide.with(this).load(intent.getStringExtra("image"))
                        .apply(RequestOptions().centerCrop())
                        .into(image)
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.menu_delete ->{
                //iDeleteItemCallback!!.deleteItem()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
