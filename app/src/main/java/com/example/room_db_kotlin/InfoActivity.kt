package com.example.room_db_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_info.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        var position = intent.getIntExtra("key", 1)

        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
            .get(M_ViewModel::class.java)

        viewModel.getInformation(position).observe(this, Observer {
            //textView3.text  = it.toString()
            key_data.text = it.toList().toString()
        })

        add_button_info.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
                viewModel.infor(Entity_Sub(position, edit_info.text.toString()))
            }
        }
    }
}