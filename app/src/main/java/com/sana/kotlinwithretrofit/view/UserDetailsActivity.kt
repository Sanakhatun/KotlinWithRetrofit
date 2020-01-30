package com.sana.kotlinwithretrofit

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserDetailsActivity : AppCompatActivity() {

    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        init()
    }

    private fun init(){
        image = findViewById(R.id.image)

        var intent = getIntent()

        if(intent != null){
            if(intent.hasExtra("image") && !intent.getStringExtra("image").equals("")){

                Glide.with(this).load(intent.getStringExtra("image"))
                    .apply(RequestOptions().centerCrop())
                    .into(image)
            }
        }
    }
}
