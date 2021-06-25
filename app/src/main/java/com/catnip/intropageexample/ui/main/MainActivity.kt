package com.catnip.intropageexample.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.catnip.intropageexample.R
import com.catnip.intropageexample.databinding.ActivityMainBinding
import com.catnip.intropageexample.ui.form.FormFragment
import com.catnip.intropageexample.ui.form.FormFragmentListener
import com.catnip.intropageexample.ui.intro.IntroFragment
import com.catnip.intropageexample.utils.views.ViewPagerAdapter

class MainActivity : AppCompatActivity(),FormFragmentListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        bind()
        initViewPager()

    }

    private fun bind() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(
            IntroFragment.newInstance(
                "This is Title",
                "This is Desc",
                R.drawable.ic_launcher_background
            ), "Fragment 1"
        )
        fragmentAdapter.addFragment(
            IntroFragment.newInstance(
                "This is Title 2",
                "This is Desc 2",
                R.drawable.ic_launcher_background
            ), "Fragment 2"
        )
        fragmentAdapter.addFragment(
            IntroFragment.newInstance(
                "This is Title 3",
                "This is Desc 3",
                R.drawable.ic_launcher_background
            ), "Fragment 3"
        )
        fragmentAdapter.addFragment(
           FormFragment(), "Fragment 4"
        )
        binding.vpIntro.apply {
            adapter = fragmentAdapter
        }
        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when {
                    position == 0 -> {
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvPrevious.visibility = View.INVISIBLE
                        binding.tvPrevious.isEnabled = false

                    }
                    position < fragmentAdapter.itemCount - 1 -> {
                        binding.tvNext.visibility = View.VISIBLE
                        binding.tvNext.isEnabled = true
                        binding.tvPrevious.visibility = View.VISIBLE
                        binding.tvPrevious.isEnabled = true
                    }
                    position == fragmentAdapter.itemCount - 1 -> {
                        binding.tvNext.visibility = View.INVISIBLE
                        binding.tvNext.isEnabled = false
                        binding.tvPrevious.visibility = View.VISIBLE
                        binding.tvPrevious.isEnabled = true
                    }
                }
                super.onPageSelected(position)
            }
        })
        binding.dotsIndicator.setViewPager2(binding.vpIntro)
        binding.tvNext.setOnClickListener {
            if (getNextIndex() != -1) {
                binding.vpIntro.setCurrentItem(getNextIndex(), true)
            }
        }
        binding.tvPrevious.setOnClickListener {
            if (getPreviousIndex() != -1) {
                binding.vpIntro.setCurrentItem(getPreviousIndex(), true)
            }
        }
    }

    private fun getPreviousIndex(): Int {
        val currentPageIdx = binding.vpIntro.currentItem
        return if (currentPageIdx - 1 >= 0) {
            currentPageIdx - 1
        } else {
            -1
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.vpIntro.adapter?.itemCount
        val currentPageIdx = binding.vpIntro.currentItem
        var selectedIdx = -1
        maxPages?.let {
            if (currentPageIdx + 1 < maxPages) {
                selectedIdx = currentPageIdx + 1
            }
        }
        return selectedIdx
    }

    override fun onNameSubmitted(text: String) {
        Toast.makeText(this, "Do navigate okay ? $text", Toast.LENGTH_SHORT).show()
    }

}