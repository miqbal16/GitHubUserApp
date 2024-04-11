package com.muhammadiqbal.githubuserapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.muhammadiqbal.githubuserapp.databinding.ActivityDetailUserBinding


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
        viewPagerConfig()
    }

    private fun viewPagerConfig() {
        val pageAdapter = PageAdapter(this, supportFragmentManager)
        binding.tabPage.adapter = pageAdapter
        binding.menuTab.setupWithViewPager(binding.tabPage)
        supportActionBar?.elevation = 0f
    }


    @SuppressLint("SetTextI18n")
    private fun setData() {
        val dataUser = intent.getParcelableExtra<DataUser>(EXTRA_DATA) as DataUser
        binding.nameDetail.text = dataUser.name.toString()
        binding.username.text = dataUser.username.toString()
        binding.usrCompany.text = "Company: " + dataUser.company.toString()
        binding.usrLocation.text = "Location:" + dataUser.location.toString()
        binding.usrRepo.text = "Repository:" + dataUser.repository.toString()
        binding.usrFollowers.text = "Follower:" + dataUser.followers.toString()
        binding.usrFollowing.text = "Following:" + dataUser.following.toString()
        Glide.with(this)
                .load(dataUser.avatar.toString())
                .into(binding.imgAvatar)
    }

}
