package com.sana.kotlinwithretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserListAdapter(val context: Context, var users: List<User>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =  UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.layout_user_row, parent, false)
        )

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int){
        holder.bindView(users[position])
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(user: User){

            val tv_userName = itemView.findViewById<TextView>(R.id.tv_userName)
            val tv_userType = itemView.findViewById<TextView>(R.id.tv_userType)
            val iv_avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)

            try{
                tv_userName.text = user.username;
                tv_userType.text = user.userType;

                Glide.with(itemView).load(user.image)
                    .apply(RequestOptions().centerCrop())
                    .into(iv_avatar)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}