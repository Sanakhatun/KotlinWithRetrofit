package com.sana.kotlinwithretrofit

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class UserActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserListAdapter
    lateinit var progerssProgressDialog: ProgressDialog
    var userList = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        init()
    }

    private fun init(){
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setMessage("Please Wait...")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        try{

            fetchData()
            userAdapter = UserListAdapter(this, userList, {user ->onItemClicked(user)})
            recyclerView.adapter = userAdapter;

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun fetchData(){
        val call: Call<List<User>> = ApiClient.create().getImages()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                progerssProgressDialog.dismiss()
            }

            override fun onResponse(call: Call<List<User>>?,response: Response<List<User>>?) {
                progerssProgressDialog.dismiss()

                try{
                    userList.addAll(response!!.body()!!)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }
        })

    }

    private fun onItemClicked(user: User){

        var intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("image",user.image)
        startActivity(intent)
    }
}
