package com.example.appointmentapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.appointmentapp.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Disable edge-to-edge mode
        WindowCompat.setDecorFitsSystemWindows(window, true)
        
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }
    }
}
