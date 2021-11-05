package com.example.first_apps.components

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.first_apps.R
import kotlinx.android.synthetic.main.activity_content_provider.*

class TestContentProviderActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_content_provider)

        initView()

    }

    private fun initView() {
        get_contact.setOnClickListener {
            getContacts()
        }

        update_contact.setOnClickListener {
            updataContacts()
        }

        insert_contact.setOnClickListener {
            addContacts()
        }

        delete_contact.setOnClickListener {
            deleteContacts()
        }
    }
    private fun getContacts() {
        val resolver = contentResolver
        println("开始")
        val uri = Uri.parse("content://com.android.contacts/data/phones")
        val cursor = resolver.query(uri,null,null,null,null)
        while (cursor!!.moveToNext()) {
            val displayName = cursor.getString(cursor.getColumnIndex("display_name"))
            val phoneNumber = cursor.getString(cursor.getColumnIndex("data1"))
            Log.e("ContentProvider","姓名：$displayName")
            Log.e("ContentProvider","号码：$phoneNumber")
        }
        cursor.close() //防止内存泄漏
    }
    private fun updataContacts() {
        val contractId = getContactIdByPhone(111)
        if(contractId ==null) {
            Toast.makeText(this,"联系人不存在，无法更新",Toast.LENGTH_SHORT).show()
            return
        }
        val values = ContentValues()
        values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,"list-update")
        val ret = contentResolver.update(
            ContactsContract.Data.CONTENT_URI, values,
            "${ContactsContract.Data.CONTACT_ID}=?", arrayOf(contractId)
        )

        if(ret>0) {
            Toast.makeText(this,"更新成功",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"更新失败",Toast.LENGTH_SHORT).show()
        }

    }

    fun getContactIdByPhone(phone:Long):String?{
        val uri = Uri.parse("content://com.android.contacts/data/phones/filter/$phone")
        val resolver = contentResolver
        val curor =  resolver.query(uri, arrayOf(ContactsContract.Data.CONTACT_ID),null,null,null)?:return null
        if(curor.moveToFirst()) {
            val contractId = curor.getString(curor.getColumnIndex(ContactsContract.Data.CONTACT_ID))
            return  contractId
        }
        return  null
    }
    private fun addContacts() {
        val resolver =contentResolver
       val values = ContentValues()
       val rawContactUri = resolver.insert(ContactsContract.RawContacts.CONTENT_URI,values)!!
       val rawContactId =  ContentUris.parseId(rawContactUri)

        //插入姓名
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId)
        values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,"i,m ok")
        val ret = resolver.insert(ContactsContract.Data.CONTENT_URI, values)
        if(ret == null) {
            Toast.makeText(this,"插入姓名成功",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"插入姓名失败",Toast.LENGTH_SHORT).show()
        }

//        插入手机号
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId)
        values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"13333333333")
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
        val ret2 = resolver.insert(ContactsContract.Data.CONTENT_URI,values)
        if(ret2 == null) {
            Toast.makeText(this,"插入手机号成功",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"插入手机号失败",Toast.LENGTH_SHORT).show()
        }

//        插入邮箱
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID,rawContactId)
        values.put(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER,"13333333333")
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE,ContactsContract.CommonDataKinds.Email.TYPE_WORK)
        val ret3 = resolver.insert(ContactsContract.Data.CONTENT_URI,values)
        if(ret3 == null) {
            Toast.makeText(this,"插入邮箱成功",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"插入邮箱失败",Toast.LENGTH_SHORT).show()
        }

    }

    private fun deleteContacts() {
        val ret = contentResolver.delete(
            ContactsContract.RawContacts.CONTENT_URI,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"=?",
            arrayOf("list")
        )
        if(ret>0) {
            Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this,"删除失败",Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestpermission() {
        val readable = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED  //判断读取权限
        val writeable = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED  //判断写权限
        if (!readable || writeable) {
            //如果未授权
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS),
                100)
        } else {
//            getContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode== 100) {
            if(permissions[0]==Manifest.permission.READ_CONTACTS){
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"读取通讯录权限成功",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this,"读取通讯录权限被拒绝，程序将无法继续工作",Toast.LENGTH_SHORT).show()
                }
            }else if(permissions[1]==Manifest.permission.WRITE_CONTACTS) {
                if(grantResults[1]==PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"写入联系人授权成功",Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(this,"写入联系人授权失败，程序将无法继续工作",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}