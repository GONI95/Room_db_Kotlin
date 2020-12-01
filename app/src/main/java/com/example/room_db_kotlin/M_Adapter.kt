package com.example.room_db_kotlin

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_rv.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class M_Adapter(val context: Context,
                var list: List<Entity_Main>,
                var viewModel: M_ViewModel,
                var lifecycleScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<M_Adapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_rv, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.disciption.text = list[position].name
        // disciption 밑에서 정의했음
        val memolist = list[position]

        println("" + list[position].id + "" + list[position].name)
        // 2

        holder.itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->   // v 리사이클러뷰 (한칸)
            menu.add(R.string.remove).setOnMenuItemClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    // 백그라운드단에서 실행되어야하니 쓰기, 읽기 작업을 해야하니 IO로 설정
                    viewModel.delete(memolist)
                    //viewModel.deleteAll()
                    v.setBackgroundColor(Color.BLACK)
                }
                true
            }
            menu.add(R.string.dataedit).setOnMenuItemClickListener {
                val dialog = AlertDialogActivity(context, list, viewModel, lifecycleScope, position)
                dialog.show()
                // 3
                true
            }
        }

        holder.itemView.submemo_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(context, InfoActivity::class.java)
                intent.putExtra("key", list[position].id)
                context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val disciption = itemView.rv_textview
    }
}