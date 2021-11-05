package com.example.first_apps

import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.first_apps.components.SecondFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val fragment = SecondFragment()

        toggle_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val childCount = group.childCount
            var selectIndex:Int = 0
            for(index in 0 until childCount) {
                val button = group.getChildAt(index) as MaterialButton
                if(button.id ==checkedId) {
                    selectIndex = index
                    //选中按钮
                    button.setTextColor(Color.RED)
                    button.iconTint = ColorStateList.valueOf(Color.RED)
                }else {
                    button.setTextColor(Color.BLACK)
                    button.iconTint = ColorStateList.valueOf(Color.BLACK)
                }
            }
            switchFragment(selectIndex)
        }
        toggle_group.check(R.id.tab1)

//        //传参
//        val bundle =Bundle()
//        bundle.putInt("key_int",100)
//        bundle.putString("key_string","value")
//        fragment.arguments = bundle
//
//        val ft = supportFragmentManager.beginTransaction()
//        if(!fragment.isAdded)  {
//            ft.add(R.id.container1,fragment)
//        }
//        ft.commitAllowingStateLoss()

//        //接收参数
//        val StringExtra = intent.getStringExtra("extra_data")
//        val inExtra = intent.getIntExtra("extra_int_data",0)
//
//        val textView = TextView(this)
//        textView.text = "SecondActivity${StringExtra}--${inExtra}"
//        textView.gravity =Gravity.CENTER
//        setContentView(textView)
//
//        Log.e("SecondActivity","onCreate")
    }

    private var tab1Fragment:SecondFragment?= null
    private var tab2Fragment:SecondFragment?= null
    private var tab3Fragment:SecondFragment?= null
    private var showuFragment: Fragment?=null
    private fun switchFragment(selectIndex: Int) {
    var fragment =  when(selectIndex) {
            0-> {
                if(tab1Fragment == null) {
                    tab1Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab","tab1")
                    tab1Fragment!!.arguments= bundle
                }
                tab1Fragment
            }
            1->{
                if(tab2Fragment == null) {
                    tab2Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab","tab2")
                    tab2Fragment!!.arguments= bundle
                }
                tab2Fragment
            }
           2->{
                if(tab3Fragment == null) {
                    tab3Fragment = SecondFragment()
                    val bundle = Bundle()
                    bundle.putString("tab","tab3")
                    tab3Fragment!!.arguments= bundle
                }
                tab3Fragment
            }
            else->{
                throw IllegalAccessError("下标不符合预期")
            }
        } ?:return

        val ft = supportFragmentManager.beginTransaction()
        if(!fragment.isAdded) {
            ft.add(R.id.container1,fragment)
        }
        ft.show(fragment)
        if(showuFragment !=null) {
            ft.hide(showuFragment!!)
        }
        showuFragment = fragment
        ft.commitAllowingStateLoss()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1000 && requestCode == Activity.RESULT_OK && data != null) {
            val stringExtra = data.getStringExtra("extra_data")
            val intExtra = data.getIntExtra("extra_int_data",0)
            //获取参数
//                ...
        }
    }
}