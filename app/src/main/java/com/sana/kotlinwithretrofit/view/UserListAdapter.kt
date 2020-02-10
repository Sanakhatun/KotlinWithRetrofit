package com.sana.kotlinwithretrofit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sana.kotlinwithretrofit.view.IDeleteItemCallback

class UserListAdapter(var context: Context, var userList: ArrayList<User>) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>(), IDeleteItemCallback {

    var position: Int = 0
    /* Initialization */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            context, LayoutInflater.from(context).inflate(R.layout.layout_user_row, parent, false)
        )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int){
        holder.bindView(userList[position], position)
        this.position = position
    }


    class UserViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(user: User, position: Int){

            val tv_userName = itemView.findViewById<TextView>(R.id.tv_userName)
            val tv_userType = itemView.findViewById<TextView>(R.id.tv_userType)
            val iv_avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)

            try{
                tv_userName.text = user.username
                tv_userType.text = user.userType

                Glide.with(itemView).load(user.image)
                    .apply(RequestOptions().centerCrop())
                    .into(iv_avatar)

                itemView.setOnClickListener{

                    var intent = Intent(context, UserDetailsActivity::class.java)
                    intent.putExtra("image",user.image)
                    intent.putExtra("selectedPosition", position)
                    context.startActivity(intent)
                }
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    public fun addItem(user: User) {
        userList.add(user)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, userList.size)
    }

    public fun removeItem(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userList.size)
    }

    override fun deleteItem() {
        removeItem(position)
    }
}