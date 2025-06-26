package com.example.appointmentapp.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appointmentapp.Adapter.CategoryAdapter
import com.example.appointmentapp.Adapter.TopDoctorAdapter
import com.example.appointmentapp.Fragment.AccountFragment
import com.example.appointmentapp.Fragment.SettingsFragment
import com.example.appointmentapp.Fragment.WishlistFragment
import com.example.appointmentapp.R
import com.example.appointmentapp.Utils.ThemeManager
import com.example.appointmentapp.ViewModel.MainViewModel
import com.example.appointmentapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Apply saved theme
        if (ThemeManager.isDarkMode(this)) {
            ThemeManager.setDarkMode(this, true)
        }
        
        // Disable edge-to-edge mode
        WindowCompat.setDecorFitsSystemWindows(window, true)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initCategory()
        initTopDoctors()
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_explorer -> {
                    // Remove any existing fragment
                    currentFragment?.let {
                        supportFragmentManager.beginTransaction()
                            .remove(it)
                            .commit()
                    }
                    currentFragment = null
                    binding.main.visibility = View.VISIBLE
                    binding.fragmentContainer.visibility = View.GONE
                    true
                }
                R.id.navigation_wishlist -> {
                    binding.main.visibility = View.GONE
                    binding.fragmentContainer.visibility = View.VISIBLE
                    loadFragment(WishlistFragment())
                    true
                }
                R.id.navigation_settings -> {
                    binding.main.visibility = View.GONE
                    binding.fragmentContainer.visibility = View.VISIBLE
                    loadFragment(SettingsFragment())
                    true
                }
                R.id.navigation_account -> {
                    binding.main.visibility = View.GONE
                    binding.fragmentContainer.visibility = View.VISIBLE
                    loadFragment(AccountFragment())
                    true
                }
                else -> false
            }
        }
        
        // Set explorer as default selected item
        binding.bottomNavigation.selectedItemId = R.id.navigation_explorer
    }

    private fun loadFragment(fragment: Fragment) {
        currentFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }

    private fun initTopDoctors() {
        binding.apply {
            progressBarTopDoctor.visibility = View.VISIBLE
            viewModel.doctors.observe(this@MainActivity, Observer { doctorsList ->
                recyclerViewTopDoctor.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewTopDoctor.adapter = TopDoctorAdapter(doctorsList)
                progressBarTopDoctor.visibility = View.GONE
            })
            viewModel.loadDoctors()
        }
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(this@MainActivity, Observer { categoryList ->
            binding.viewCategory.layoutManager = LinearLayoutManager(
                this@MainActivity, LinearLayoutManager.HORIZONTAL, false
            )
            binding.viewCategory.adapter = CategoryAdapter(categoryList)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }
}
