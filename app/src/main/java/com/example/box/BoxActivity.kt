package com.example.box

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.box.databinding.AcrivityBoxBinding

class BoxActivity : AppCompatActivity() {

    private lateinit var binding: AcrivityBoxBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcrivityBoxBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.toMainMenuButton.setOnClickListener {  onToMainMenuPressed() }
    }

    private fun onToMainMenuPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
    }

}