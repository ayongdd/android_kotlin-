package com.example.first_apps.components

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SecondFragment:Fragment() {
    override fun onAttach(context: Context) { //fragment被绑定到Activity时调用
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) { //创建
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        //获取Activity传的参数
        val stringArgment = arguments?.getString("tab")

//        Log.e("params1",intArgument.toString())
        Log.e("params",stringArgment.toString())

        val textView = TextView(context)
        textView.text = "params${stringArgment}"
        textView.gravity=Gravity.CENTER

        //跳转到ServiceActivity
//        textView.setOnClickListener {
//            startActivity(Intent(context,TextServiceActivity::class.java))
//        }
        textView.setOnClickListener {
            startActivity(Intent(context,TestBroadcaseRecevierActivity::class.java))
        }
        return textView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        //当且仅当activity有多个fragment且调用ft.show或ft.hide时才触发
        //hidden:当前fragment 可见的时候 hidden =false,ft.show
        //hidden:当前fragment不可见的时候hidden =true,ft.hide
        Log.e("SecondFragment","${arguments?.getString("tab")}--${hidden}")
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() { //onCreateView返回的view对象被销毁的时候，会执行这个回调
        super.onDestroyView()
    }

    override fun onDestroy() { //fragment被销毁的时候调用
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }
}