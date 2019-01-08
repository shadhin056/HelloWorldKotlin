package com.example.shadhin.helloworldkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RestApiCall : AppCompatActivity() {

    private var etxtUserName: EditText? = null
    private var etxtPassword: EditText? = null
    private var btnSubmitMOD5: Button? = null
    private var btnSubmitSHA2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_api_call)
        initView()
        define()
    }

    private fun define() {

        btnSubmitMOD5!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", etxtUserName!!.text.toString())
            intent.putExtra("etxtPassword", etxtPassword!!.text.toString())
            startActivity(intent)
        }
        btnSubmitSHA2!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", etxtUserName!!.text.toString())
            intent.putExtra("etxtPassword", etxtPassword!!.text.toString())
            startActivity(intent)
        }
    }

    private fun initView() {

        etxtUserName = findViewById(R.id.etxtUserName) as EditText
        etxtPassword = findViewById(R.id.etxtPassword) as EditText
        btnSubmitMOD5 = findViewById(R.id.btnSubmitMOD5) as Button
        btnSubmitSHA2 = findViewById(R.id.btnSubmitSHA2) as Button
    }
}
