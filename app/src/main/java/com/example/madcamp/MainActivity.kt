package com.example.madcamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var profileAdapter: ProfileAdapter
    val datas = mutableListOf<ProfileData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pagerAdapter = FragmentAdapter(supportFragmentManager)
        val pager = findViewById<ViewPager>(R.id.viewpager)
        pager.adapter = pagerAdapter
        val tab = findViewById<TabLayout>(R.id.tablayout)
        tab.setupWithViewPager(pager)
//        initRecycler()
    }

//    private fun initRecycler() {
//        profileAdapter = ProfileAdapter(this)
//
//        val profile = findViewById<RecyclerView>(R.id.rv_profile)
//        profile.adapter = profileAdapter
//
//
//        datas.apply {
//            add(ProfileData(name = "Jaein Kim", age = 24))
//            add(ProfileData(name = "Dongwon Choi", age = 23))
//
//            profileAdapter.datas = datas
//            profileAdapter.notifyDataSetChanged()
//
//        }
//    }
}