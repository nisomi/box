package com.example.box

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.box.databinding.ActivityOptionsBinding
import com.example.box.models.Option

class OptionsActivity: AppCompatActivity() {
    private lateinit var binding: ActivityOptionsBinding

    private lateinit var options: Option

    private lateinit var boxCountItems: List<BoxCountItem>
    private lateinit var adapter: ArrayAdapter<BoxCountItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptionsBinding.inflate(layoutInflater).also { setContentView(it.root) }

        options = savedInstanceState?.getParcelable<Option>(KEY_OPTIONS) ?:
                intent?.getParcelableExtra(EXTRA_OPTIONS) ?:
                throw IllegalArgumentException("You need to specify EXTRA_OPTIONS to launch this activity")

        setupSpinner()
        updateUi()

        binding.cancelButton.setOnClickListener { onCancelPressed() }
        binding.confirmButton.setOnClickListener { onConfirmPressed() }
    }

    private fun setupSpinner() {
        boxCountItems = (1..6).map { BoxCountItem(it, resources.getQuantityString(R.plurals.boxes, it, it)) }
        adapter = ArrayAdapter(
            this,
            R.layout.item_spinner,
            boxCountItems
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

        binding.boxCountSpinner.adapter = adapter
        binding.boxCountSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val count = boxCountItems[position].count
                options = options.copy(boxCount = count)
            }
        }
    }

    private fun updateUi() {

        val currentIndex = boxCountItems.indexOfFirst { it.count == options.boxCount }
        binding.boxCountSpinner.setSelection(currentIndex)
    }

    private fun onCancelPressed() {
        finish()
    }

    private fun onConfirmPressed() {
        val intent = Intent()
        intent.putExtra(EXTRA_OPTIONS, options)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    companion object {
        const val EXTRA_OPTIONS = "EXTRA_OPTIONS"

        private const val KEY_OPTIONS = "KEY_OPTIONS"
    }

    class BoxCountItem(
        val count: Int,
        private val optionTitle: String
    ) {
        override fun toString(): String {
            return optionTitle
        }
    }
}