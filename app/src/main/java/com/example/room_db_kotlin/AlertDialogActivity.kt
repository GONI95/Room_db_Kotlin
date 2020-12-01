package com.example.room_db_kotlin

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//https://chuumong.github.io/android/2017/01/16/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4
//https://www.slideshare.net/VladislavErmolin/dialogs-in-android-mvvm-14112019
//https://blog.yena.io/studynote/2019/03/27/Android-MVVM-AAC-2.html


//https://webnautes.tistory.com/1094
class AlertDialogActivity(context: Context,
                          var list: List<Entity_Main>,
                          var viewModel: M_ViewModel,
                          var lifecycleScope: LifecycleCoroutineScope,
                          var position : Int) : Dialog(context) {

    override fun show() {
        val dialogView = LayoutInflater.from(context)
                .inflate(R.layout.custom_dialog, null)
        val dialogText = dialogView.findViewById<EditText>(R.id.dialog_ed)
        val builder = AlertDialog.Builder(context)
        builder.setIcon(R.drawable.ic_launcher_background)
        builder.setTitle("memo_table")
        builder.setMessage("다이어로그를 누르셨습니다. 원하는 작업을 하세요.")
        builder.setView(dialogView)
                .setPositiveButton("확인") { dialogInterface, i ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
                        viewModel.update(list[position].id, dialogText.text.toString())
                        println("" + list[position].id + "" + dialogText.text.toString())
                    }
                }
                .setNegativeButton("취소") { dialogInterface, i ->
                    /* 취소일 때 아무 액션이 없으므로 빈칸 */
                }.show()
    }


}

/*
val dialogView = LayoutInflater.from(context)
            .inflate(R.layout.custom_dialog, null)
    val dialogText = dialogView.findViewById<EditText>(R.id.dialog_ed)
    val builder = AlertDialog.Builder(context)
    builder.setIcon(R.drawable.ic_launcher_background)
    builder.setTitle("memo_table")
    builder.setMessage("다이어로그를 누르셨습니다. 원하는 작업을 하세요.")
    builder.setView(dialogView)
    .setPositiveButton("확인") { dialogInterface, i ->
        lifecycleScope.launch(Dispatchers.IO) {
            // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
            viewModel.update2(list[position].id, dialogText.text.toString())
            println("" + list[position].id + "" + dialogText.text.toString())
        }
    }
    .setNegativeButton("취소") { dialogInterface, i ->
        /* 취소일 때 아무 액션이 없으므로 빈칸 */
    }
    .show()
 */