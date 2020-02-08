package com.sana.kotlinwithretrofit

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sana.kotlinwithretrofit.common.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*

class UserDetailsActivity : BaseActivity() {

    lateinit var image: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        initialise()
    }

    private fun initialise(){
        super.init()
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.menu_delete ->{
                Toast.makeText(this, "Item Two Clicked", Toast.LENGTH_LONG).show()
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
