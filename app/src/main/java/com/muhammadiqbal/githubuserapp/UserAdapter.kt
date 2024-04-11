package com.muhammadiqbal.githubuserapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import kotlin.collections.ArrayList

var userFilterList = ArrayList<DataUser>()

class UserAdapter(private var listData: ArrayList<DataUser>) :
        RecyclerView.Adapter<UserAdapter.ListDataHolder>(), Filterable {

    init {
        userFilterList = listData
    }

    inner class ListDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageAvatar: CircleImageView = itemView.findViewById(R.id.img_user)
        var username: TextView = itemView.findViewById(R.id.txt_username)
        var name: TextView = itemView.findViewById(R.id.name_usr)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(dataUsers: DataUser)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListDataHolder {
        val view: View = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.user_item, viewGroup, false)
        val sch = ListDataHolder(view)
        context = viewGroup.context
        return sch
    }

    override fun getItemCount(): Int {
        return userFilterList.size
    }

    override fun onBindViewHolder(holder: ListDataHolder, position: Int) {
        val data = userFilterList[position]
        Glide.with(holder.itemView.context)
                .load(data.avatar)
                .apply(RequestOptions().override(250, 250))
                .into(holder.imageAvatar)
        holder.name.text = data.name
        holder.username.text = data.username
        holder.itemView.setOnClickListener {
            val dataUser = DataUser(
                    data.username,
                    data.name,
                    data.avatar,
                    data.company,
                    data.location,
                    data.repository,
                    data.followers,
                    data.following
            )
            val intentDetail = Intent(context, DetailUserActivity::class.java)
            intentDetail.putExtra(DetailUserActivity.EXTRA_DATA, dataUser)
            context.startActivity(intentDetail)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charSearch = constraint.toString()
                userFilterList = if (charSearch.isEmpty()) {
                    listData
                } else {
                    val resultList = ArrayList<DataUser>()
                    for (row in userFilterList) {
                        if ((row.username.toString().toLowerCase(Locale.ROOT)
                                        .contains(charSearch.toLowerCase(Locale.ROOT)))
                        ) {
                            resultList.add(
                                    DataUser(
                                            row.name,
                                            row.username,
                                            row.avatar,
                                            row.company,
                                            row.location,
                                            row.followers,
                                            row.following,
                                            row.repository
                                    )
                            )
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                userFilterList = results.values as ArrayList<DataUser>
                notifyDataSetChanged()
            }
        }
    }
}