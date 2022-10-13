package com.example.datestoredemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.datestoredemo.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.button.setOnClickListener {
            lifecycleScope.launch{
//                DataStoreU.setDs(this@MainActivity,"ooo",binding.editTextTextPersonName.text)
                ProtoDataStoreU.setDs(this@MainActivity,binding.editTextTextPersonName.text.toString())
            }
        }

        binding.button2.setOnClickListener {
            lifecycleScope.launch{
//                DataStoreU.getDs(this@MainActivity,"ooo","youbing").collect {
//                    binding.textView.text = it.toString()
//                }
//                ProtoDataStoreU.getDs(this@MainActivity).collect{
//                    binding.textView.text = it.toString()
//                }
//                binding.textView.text = DataStoreU.getDs(this@MainActivity,"ooo","ffff") as String
                binding.textView.text = ProtoDataStoreU.getSyncDs(this@MainActivity)
            }
        }

        binding.button3.setOnClickListener { binding.textView.text="" }
    }
}