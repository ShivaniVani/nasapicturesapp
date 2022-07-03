package com.example.test.ui.pictures_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.PictureSingleItemBinding
import com.example.test.ui.pictures_list.Pictures

class UserListAdapter(private val context: Context,val onItemClickInterface:onItemClick,val list : List<Pictures>) :
    RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListViewHolder {
        val binding: PictureSingleItemBinding =
            PictureSingleItemBinding.inflate(
                LayoutInflater.from(context), parent, false
            )
        return UserListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val data = list[position]
        Glide.with(context)
            .load(data.url)
            .placeholder(R.drawable.ic_user).into(holder.binding.ivUser)
        holder.binding.mainCard.setOnClickListener {
            onItemClickInterface.onItemClick(data.url)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class UserListViewHolder(val binding: PictureSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root)



    interface onItemClick {
        fun onItemClick(url:String)

    }


}
