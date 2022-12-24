package com.example.lwdaggerroomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lwdaggerroomdemo.databinding.ActivityMainBinding
import com.example.lwdaggerroomdemo.db.UserEntity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       with(binding) {
            saveButton.setOnClickListener {
                val userEntity = UserEntity(name = enterDescriptionEditText.text.toString())
                viewModel.insertRecord(userEntity)
                enterDescriptionEditText.setText("")
            }
        }

        initVM()
    }

    private fun initVM() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this, object : Observer<List<UserEntity>> {
            override fun onChanged(t: List<UserEntity>?) {
                binding.resultTextView.setText("")
                t?.forEach{
                    binding.resultTextView.append(it.name + "\n")
                }
            }
        })
    }
}