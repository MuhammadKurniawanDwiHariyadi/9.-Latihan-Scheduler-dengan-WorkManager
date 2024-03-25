package com.dicoding.myworkmanager.ui

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.dicoding.myworkmanager.R
import com.dicoding.myworkmanager.databinding.ActivityMainBinding
import com.dicoding.myworkmanager.utils.MyWorker

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var workManager: WorkManager

    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "Notifications permission granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifications permission rejected", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        workManager = WorkManager.getInstance(this)

        binding.BTOneTimeTask.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.BTOneTimeTask -> startOneTimeTask()
        }
    }

    private fun startOneTimeTask() {
        binding.TVStatus.text = getString(R.string.status)
        val city = binding.ETCity.text.toString()

        val data = Data.Builder()
            .putString(MyWorker.EXTRA_CITY, city)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWorker::class.java)
            .setInputData(data)
            .build()

        workManager.enqueue(oneTimeWorkRequest)
        workManager.getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this@MainActivity) { workInfo->
                val status = workInfo.state.name
                binding.TVStatus.append("\n + $status")

            }
    }
}