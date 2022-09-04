package com.task.virtusa.activity

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.task.virtusa.databinding.LayoutLoadingBinding
import com.task.virtusa.databinding.LayoutTask2Binding
import com.task.virtusa.mvvm.MainViewModel

class ActivityTask2 : AppCompatActivity() {

    lateinit var binding: LayoutTask2Binding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutTask2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = "Task 2"

        if (checkNetworkAvailable(this@ActivityTask2)) {
            initViewModel()
        }

        binding.textEmail1.setOnClickListener {
            if (checkNetworkAvailable(this@ActivityTask2)) {
                viewModel.getData("1")
            } else {
                showToast("Check your internet connection")
            }
        }

        binding.textEmail2.setOnClickListener {
            if (checkNetworkAvailable(this@ActivityTask2)) {
                viewModel.getData("3")
            } else {
                showToast("Check your internet connection")
            }
        }

        binding.textEmail3.setOnClickListener {
            if (checkNetworkAvailable(this@ActivityTask2)) {
                viewModel.getData("10")
            } else {
                showToast("Check your internet connection")
            }
        }


    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.loading.observe(this@ActivityTask2, Observer {
            if (it) {
                showDialogLoading(this@ActivityTask2, "Loading...", false).show()
            } else {
                dialogLoading.dismiss()
            }
        })

        viewModel.getData.observe(this, Observer {

            if (it.data!!.email != "") {
                binding.textOutput.text = it.data.email
            }

        })

        viewModel.error.observe(this, Observer {
            showToast(it.toString())
        })
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
        Toast.makeText(this@ActivityTask2, "" + message, Toast.LENGTH_SHORT).show()
    }

    lateinit var dialogLoading: Dialog

    fun showDialogLoading(context: Context?, title: String?, boolean: Boolean): Dialog {
        dialogLoading = Dialog(context!!)
        val binding: LayoutLoadingBinding =
            LayoutLoadingBinding.inflate(LayoutInflater.from(context))
        dialogLoading.run { setContentView(binding.root) }
        if (dialogLoading.window != null) {
            dialogLoading.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        binding.title.text = title.toString()
        dialogLoading.setCancelable(boolean)
        dialogLoading.setCanceledOnTouchOutside(boolean)
        return dialogLoading
    }

    fun checkNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                //for check internet over Bluetooth
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

}