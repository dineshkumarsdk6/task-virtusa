package com.task.virtusa.activity

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.task.virtusa.databinding.LayoutTask1Binding


class ActivityTask1 : AppCompatActivity() {

    lateinit var binding: LayoutTask1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutTask1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Task 1"

        binding.textSubmit.setOnClickListener {
            if (binding.editTextInput1.text.toString().isEmpty()){
                showToast("Enter Input1")
                return@setOnClickListener
            }
            if (binding.editTextInput2.text.toString().isEmpty()){
                showToast("Enter Input2")
                return@setOnClickListener
            }

            binding.textInput1.text = binding.editTextInput1.text.toString()
            binding.textInput2.text = binding.editTextInput2.text.toString()
            binding.textOutput1.text = getOutput1(binding.editTextInput1.text.toString(), binding.editTextInput2.text.toString())
            binding.textOutput2.text = getOutput2(binding.editTextInput1.text.toString(), binding.editTextInput2.text.toString())
            binding.cardOutput.visibility = View.VISIBLE
        }

    }

    private fun getOutput1(input1: String, input2: String): String {
        val output = StringBuffer()
        for (i in input1) {
            var flag = 0
            for (j in input2) {
                if (i.lowercaseChar() == j.lowercaseChar()) {
                    flag = 1
                }
            }
            if (flag != 1) output.append(i)
        }
        return output.toString()
    }

    private fun getOutput2(input1: String, input2: String): String {
        val output = StringBuffer()
        for (i in input2) {
            var flag = 0
            for (j in input1) {
                if (i.lowercaseChar() == j.lowercaseChar()) {
                    flag = 1
                }
            }
            if (flag != 1) output.append(i)
        }
        return output.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@ActivityTask1, "" + message, Toast.LENGTH_SHORT).show()
    }
}