package com.sana.kotlinwithretrofit

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sana.kotlinwithretrofit.common.BaseActivity
import com.sana.kotlinwithretrofit.utilities.Constants.REQUEST_CODE
import com.sana.kotlinwithretrofit.utilities.Constants.RESULT_CODE
import com.sana.kotlinwithretrofit.utilities.Utilities
import com.sana.kotlinwithretrofit.view.AddItemDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class UserActivity : BaseActivity(), View.OnClickListener{
    lateinit var fab_addItem: FloatingActionButton
    lateinit var recyclerView: RecyclerView
    lateinit var userAdapter: UserListAdapter
    lateinit var progerssProgressDialog: ProgressDialog
    var userList = ArrayList<User>()
    var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        initialise()
    }

    private fun initialise() {
        super.init()
        toolbar?.setTitle("User")

        /* To hide navigationIcon */
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        fab_addItem = findViewById(R.id.fab_addItem)
        recyclerView = findViewById(R.id.recyclerView)
        fab_addItem.setOnClickListener(this)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        progerssProgressDialog = ProgressDialog(this)
        progerssProgressDialog.setTitle("Loading")
        progerssProgressDialog.setMessage("Please Wait...")
        progerssProgressDialog.setCancelable(false)
        progerssProgressDialog.show()

        try {

            fetchData()
            userAdapter = UserListAdapter(this, userList, {user,position -> onItemClicked(user,position)})
            recyclerView.adapter = userAdapter;

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /* To hide menu */
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        var deleteItem = menu!!.findItem(R.id.menu_delete)
        deleteItem.setVisible(false)
        return true
    }

    private fun fetchData() {
        val call: Call<List<User>> = ApiClient.create().getImages()
        call.enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                progerssProgressDialog.dismiss()
                Utilities.showAlert(this@UserActivity, getString(R.string.check_internet_connection))
            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                progerssProgressDialog.dismiss()

                try {
                    if (response != null && response.isSuccessful && response.body() != null) {
                        userList.addAll(response!!.body()!!)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun onItemClicked(user: User, position: Int){

        this.position = position
        var intent = Intent(this, UserDetailsActivity::class.java)
        intent.putExtra("username",user.username)
        intent.putExtra("userType",user.userType)
        intent.putExtra("image",user.image)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode.equals(RESULT_CODE)){
            try{
                userAdapter.removeItem(position)
                Toast.makeText(this, R.string.user_deleted, Toast.LENGTH_LONG).show()

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.fab_addItem -> {
                    var dialog = AddItemDialog(this)
                    dialog.show()
                    dialog.window!!.setLayout(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    dialog.onAddItem(object : AddItemDialog.IAddItemCallback {
                        override fun addItem(user: User) {

                            userAdapter.addItem(user)

                        }
                    })
                }
            }
        }
    }
}

