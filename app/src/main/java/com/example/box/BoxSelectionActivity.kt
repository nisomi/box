package com.example.box

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.box.databinding.AcrivityBoxSelectionBinding
import com.example.box.databinding.ItemBoxBinding
import com.example.box.models.Option
import kotlin.properties.Delegates
import kotlin.random.Random

class BoxSelectionActivity: AppCompatActivity() {
    private lateinit var binding: AcrivityBoxSelectionBinding

    private lateinit var options: Option

    private var boxIndex by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AcrivityBoxSelectionBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options = intent.getParcelableExtra(EXTRA_OPTIONS) ?:
                throw IllegalArgumentException("Can't launch BoxSelectionActivity without options")
        boxIndex = savedInstanceState?.getInt(KEY_INDEX) ?: Random.nextInt(options.boxCount)

        createBoxes()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_INDEX, boxIndex)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    private fun createBoxes() {
        val boxBindings = (0 until options.boxCount).map {index ->
            val boxBinding = ItemBoxBinding.inflate(layoutInflater)
            boxBinding.root.id = View.generateViewId()
            boxBinding.boxTitleTextView.text = getString(R.string.box_title, index + 1)
            boxBinding.root.setOnClickListener { view -> onBoxSelected(view) }
            boxBinding.root.tag = index
            binding.root.addView(boxBinding.root)
            boxBinding
        }

        binding.flow.referencedIds = boxBindings.map { it.root.id }.toIntArray()
    }

    private fun onBoxSelected(view: View) {
        if (view.tag as Int == boxIndex) {
            val intent = Intent(this, BoxActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, R.string.empty_box, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
        return true
    }

    companion object {
        const val EXTRA_OPTIONS = "EXTRA_OPTIONS"
        private const val KEY_INDEX = "KEY_INDEX"
        private const val KEY_ALREADY_DONE = "KEY_ALREADY_DONE"
    }
}