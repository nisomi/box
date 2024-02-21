package com.example.box

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.box.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater).also { setContentView(it.root) }
        binding.versionNameTextView.text = BuildConfig.VERSION_NAME
        binding.versionCodeTextView.text = BuildConfig.VERSION_CODE.toString()
        binding.okButton.setOnClickListener { onOkPressed() }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun onOkPressed() {
        finish()
    }
}