package com.task.virtusa.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.task.virtusa.databinding.LayoutSplashScreenBinding
import kotlinx.coroutines.*

class ActivitySplashScreen : AppCompatActivity() {

    lateinit var binding: LayoutSplashScreenBinding

    private val job: CompletableJob = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splash()
    }


    private fun splash() {
        CoroutineScope(Dispatchers.Main).launch(job) {
            delay(2500)
            val intent = Intent(this@ActivitySplashScreen, ActivityHome::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}