package com.sana.kotlinwithretrofit

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sana.kotlinwithretrofit.common.BaseActivity
import com.sana.kotlinwithretrofit.utilities.Constants.RESULT_CODE
import java.lang.Exception

class UserDetailsActivity : BaseActivity(){

    lateinit var image: ImageView
    var userType: String = ""
    var userName: String = ""

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

                if(intent.hasExtra("username")){
                    userName = intent.getStringExtra("username")
                }

                if(intent.hasExtra("userType")){
                    userType = intent.getStringExtra("userType")
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

        toolbar!!.setTitle(userName)
        toolbar!!.setSubtitle(userType)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.menu_delete ->{

                var intent = Intent(this, UserActivity::class.java)
                setResult(RESULT_CODE, intent)
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
