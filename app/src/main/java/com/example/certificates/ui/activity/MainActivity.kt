package com.example.certificates.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.certificates.R
import com.example.certificates.databinding.ActivityMainBinding
import com.example.certificates.ui.adapter.CertificatesAdapter
import com.example.certificates.ui.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var mainAdapter: CertificatesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpViewModel()
        setUpView()
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getCertificateData(this)
    }

    private fun setUpView() {
        mainAdapter = CertificatesAdapter()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }
        viewModel.certificateMutableLiveData.observe(this) {
            mainAdapter.setData(it)
            binding.progressBar.isVisible = false
            binding.recyclerView.isVisible = true
        }
    }
}