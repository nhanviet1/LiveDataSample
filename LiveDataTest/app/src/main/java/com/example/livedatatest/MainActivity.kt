package com.example.livedatatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.livedatatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: viewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView(){
        binding.button1.setOnClickListener {
            viewModel.randomNumber1()
        }

        binding.button2.setOnClickListener {
            viewModel.randomNumber2()
        }

//        viewModel.mediatorLiveData.observe(this){
//            binding.tvHolder.text = it.toString()
//        }

        viewModel.switchMap.observe(this){
            binding.tvHolder.text = it.toString()
        }


//        viewModel.checkSwitch.observe(this){
//            binding.tvHolder.text = it.toString()
//        }

        val switch = binding.switchA
        viewModel.changeSwitch(switch.isChecked)
        switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.changeSwitch(b)
            } else {
                viewModel.changeSwitch(b)
            }
        }

    }
}