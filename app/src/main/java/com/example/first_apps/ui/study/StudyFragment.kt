package com.example.first_apps.ui.study

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.first_apps.R
import com.example.first_apps.http.*
import com.example.first_apps.ui.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_study.*
import kotlinx.android.synthetic.main.item_fragment_study.view.*
import kotlinx.android.synthetic.main.item_view_grid.view.*
import kotlinx.android.synthetic.main.item_view_grid.view.item_image
import kotlinx.android.synthetic.main.item_view_grid.view.item_title
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudyFragment:Fragment(R.layout.fragment_study) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StudyAdapter();
        recycler_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recycler_view.adapter = adapter

        //请求异步数据
        HiRetrofit.create(ApiService::class.java)
            .getStudy("").enqueue(object :Callback<StudyType> {
                override fun onFailure(call: Call<StudyType>, t: Throwable) {
                    Log.e("Retrofit",t.message?:"error")
                }

                override fun onResponse(call: Call<StudyType>, response: Response<StudyType>) {
//                    Log.e("resd11",response?.body().toString() ?:"err")
                    //非空判断
                    response.body()?.let {
                        adapter.setDatas(it.data)
                    }
                }
            })

        //新增
        zengjia.setOnClickListener{
            val course = StudyData(
                "https://img.mukewang.com/szimg/5fcdf2fe09455bf105400304.jpg",
                "34",
                "从0开始 独立完成企业级Java电商网站服务端开发(长期维护) ",
                "【毕设】前后端分离，数据库接口设计，架构设计，功能开发，上线运维"
            )
            adapter.addStudyData(course)

        }

        //删除
        shanchu.setOnClickListener{
            adapter.removeStudyData(0)
        }

        //更新
        gengxing.setOnClickListener {
            adapter.updataCourse(0,"80")
        }
    }

    inner class StudyAdapter : RecyclerView.Adapter<StudyViewHolder>() {
        private val courses = mutableListOf<StudyData>()
        private val url = "https://img.mukewang.com/szimg/5fcdf2fe09455bf105400304.jpg";
        fun setDatas(datas:List<StudyData>) {
            if(datas.isNotEmpty()) {
                courses.addAll(datas)
                notifyDataSetChanged() //重新执行 getItemCount 和onBindViewHolder
            }
        }

        fun addStudyData(item:StudyData) {
            courses.add(0,item) //插入到第一条
            notifyItemInserted(0)//插入到第一条
//            notifyDataSetChanged()
//            notifyItemInserted(courses.size-1)   //插入到最后一条
            recycler_view.scrollToPosition(0)  //滚动到顶部
        }

        fun removeStudyData(position:Int) {
            courses.removeAt(position)
            notifyItemRemoved(0) //删除第一条
//            notifyDataSetChanged()
//            notifyItemInserted(courses.size-1)   //删除最后一条
        }

        fun updataCourse (position: Int,cat_id:String) {
            var couse = courses[position]
            couse.cat_id = cat_id
            notifyItemChanged(position)
//            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudyViewHolder {
            val view =
                LayoutInflater.from(context).inflate(R.layout.item_fragment_study,parent,false)
            return  StudyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return courses.size
        }

        override fun onBindViewHolder(holder: StudyViewHolder, position: Int) {
            val course = courses[position]


            //给图片设置圆角和图片大小
            val options = RequestOptions().transform(RoundedCorners(10))
                .error(R.drawable.launch_screen)
            //加载图片
            Glide.with(context!!).load(course.app_cover_pic).apply(options).into(holder.itemView.item_image)
//            holder.itemView.item_image.setImageResource(R.drawable.launch_screen)
            holder.itemView.item_title.text = "【${position}】${course.name}"
            holder.itemView.item_label.text = course.short_description
            holder.itemView.item_course_progress.text = "已学。${course.cat_id}%"

        }

    }
   inner class StudyViewHolder(view: View):RecyclerView.ViewHolder(view) {}

}




