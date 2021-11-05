package com.example.first_apps.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.first_apps.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_view_linear_vertical.view.*

//实现下拉列表布局
class HomeFragment : Fragment(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false) //垂直滚动布局
//        recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false) //横向滚动布局

//        recycler_view.layoutManager = GridLayoutManager(context,2) //grid网格布局

        recycler_view.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL) //瀑布流布局

        recycler_view.adapter = MyAdapter()
    }
    inner class MyAdapter:RecyclerView.Adapter<MyViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//            val itemView = LayoutInflater.from(context).inflate(R.layout.item_view_linear_vertical,parent,false)
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_view_grid,parent,false)
            return  MyViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return  20
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            if(position == 1 || position == 3 || position == 6 || position == 7 || position== 9) { //不显示一行 成瀑布流布局的
                holder.itemView.item_message.setSingleLine(false)
            }else {
                holder.itemView.item_message.setSingleLine(true)
            }

            holder.itemView.item_image.setImageResource(R.drawable.launch_screen)
//           holder.itemView.item_image.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.launch_screen))
//           holder.itemView.item_image.setImageBitmap(BitmapFactory.decodeResource(context!!.resources,R.drawable.launch_screen))
            holder.itemView.item_title.text = "【${position}】移动端架构师体系课"
            holder.itemView.item_message.text="移动开发两极分化，没有差不多的中间层，唯有尽早成长为架构师模拟的职业道路才能走得更远"
        }

    }

    class MyViewHolder(view:View):RecyclerView.ViewHolder(view) {}
}