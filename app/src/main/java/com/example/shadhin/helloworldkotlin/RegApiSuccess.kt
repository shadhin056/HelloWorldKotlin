package com.example.shadhin.helloworldkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class RegApiSuccess : AppCompatActivity() {

    private var txtAfterLoginUserNameDisplay: TextView? = null
    private var txtAfterLoginPassword: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_api_success)
        initView()
        receivingValue()
    }

    private fun receivingValue() {
        val intent = intent
        val userName = intent.getStringExtra("etxtUserName")
        val password = intent.getStringExtra("etxtPassword")
        txtAfterLoginUserNameDisplay!!.text = userName
        txtAfterLoginPassword!!.text = password
         }

    private fun initView() {
        txtAfterLoginUserNameDisplay = findViewById(R.id.txtAfterLoginUserNameDisplay) as TextView
        txtAfterLoginPassword = findViewById(R.id.txtAfterLoginPassword) as TextView
    }
}
