package com.example.dogs.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dogs.BuildConfig
import com.example.dogs.R
import com.example.dogs.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val titles = arrayOf("Dog", "History")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        BuildConfig.API_URI
    }


    private fun init() {
        with(binding) {
            viewPager.adapter = ViewPagerFragmentAdapter(this@MainActivity)
            TabLayoutMediator(tab, viewPager) { tab, position ->
                tab.text = titles[position]
            }.attach()
        }

    }

    private inner class ViewPagerFragmentAdapter(
        fragmentActivity: FragmentActivity
    ) :
        FragmentStateAdapter(fragmentActivity) {
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return DogFragment()
                1 -> return HistoryFragment()
            }
            return DogFragment()
        }

        override fun getItemCount(): Int {
            return titles.size
        }
    }
}