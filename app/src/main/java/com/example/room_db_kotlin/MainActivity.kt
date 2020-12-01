package com.example.room_db_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : M_ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application))
                .get(M_ViewModel::class.java)

        viewModel.getMemo().observe(this, Observer {
            //textView3.text  = it.toString()
            recyclerView.adapter = M_Adapter(this, it.toList(), viewModel, lifecycleScope)
        })

        add_button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
                viewModel.insert(Entity_Main(editText.text.toString()))
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.option_deleteAll -> {
                lifecycleScope.launch(Dispatchers.IO) {
                    // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
                    viewModel.deleteAll()
                }
            }
            R.id.option2 -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }
}