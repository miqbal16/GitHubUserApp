package com.muhammadiqbal.githubuserapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView

var followingFilterList = ArrayList<DataFollowing>()

class FollowingAdapter(listData: ArrayList<DataFollowing>) :
        RecyclerView.Adapter<FollowingAdapter.ListDataHolder>() {

    init {
        followingFilterList = listData
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageAvatar: CircleImageView = itemView.findViewById(R.id.img_user)
        var name: TextView = itemView.findViewById(R.id.name_usr)
        var username: TextView = itemView.findViewById(R.id.txt_username)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(DataFollowing: DataFollowing)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListDataHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.user_item, viewGroup, false)
        val sch = ListDataHolder(view)
        context = viewGroup.context
        return sch
    }

    override fun getItemCount(): Int {
        return followingFilterList.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = followingFilterList[position]
        Glide.with(holder.itemView.context)
                .load(data.avatar)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imageAvatar)
        holder.name.text = data.name
        holder.username.text = data.username
        holder.itemView.setOnClickListener {
        }
    }
}