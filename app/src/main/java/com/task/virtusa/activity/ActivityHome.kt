package com.task.virtusa.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.task.virtusa.databinding.ActivityHomeBinding

class ActivityHome : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textTask1.setOnClickListener {
            val intent = Intent(this@ActivityHome, ActivityTask1::class.java)
            startActivity(intent)
        }

        binding.textTask2.setOnClickListener {
            val intent = Intent(this@ActivityHome, ActivityTask2::class.java)
            startActivity(intent)
        }
    }
}