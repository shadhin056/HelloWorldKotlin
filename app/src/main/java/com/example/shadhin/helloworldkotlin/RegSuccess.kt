package com.example.shadhin.helloworldkotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_reg_success.*

class RegSuccess : AppCompatActivity() {
    private var txtAfterLoginUserNameDisplay: TextView? = null
    private var txtAfterLoginNameDisplay: TextView? = null
    private var txtAfterLoginPhoneDisplay: TextView? = null
    private var txtAfterLoginBirthdayDisplay: TextView? = null
    private var txtAfterLoginEmail: TextView? = null
    private var txtAfterLoginPassword: TextView? = null
    private var txtAfterLoginGender: TextView? = null
    private var txtAfterLoginCountry: TextView? = null
    private var txtAgree: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_success)

        initView()
        receivingValue()

        setSupportActionBar(toolbar)
       /* fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
    }

    private fun receivingValue() {
        val intent = intent
        val userName = intent.getStringExtra("etxtUserName")
        val nickName = intent.getStringExtra("etxtNickName")
        val phoneNumer = intent.getStringExtra("etxtPhoneNumer")
        val birthday = intent.getStringExtra("etxtBirthday")
        val email = intent.getStringExtra("etxtEmail")
        val password = intent.getStringExtra("etxtPassword")
       // val presentCountry = intent.getStringExtra("spPresentCountry")
       // val agree = intent.getStringExtra("chkAgree")
        //val gender = intent.getStringExtra("raGender")
        txtAfterLoginUserNameDisplay!!.text = userName
        txtAfterLoginNameDisplay!!.text = nickName
        txtAfterLoginPhoneDisplay!!.text = phoneNumer
        txtAfterLoginBirthdayDisplay!!.text = birthday
        txtAfterLoginEmail!!.text = email
        txtAfterLoginPassword!!.text = password
       // txtAfterLoginGender!!.text = presentCountry
       // txtAfterLoginCountry!!.text = agree
        //txtAgree!!.text = gender
    }

    private fun initView() {
        txtAfterLoginUserNameDisplay = findViewById(R.id.txtAfterLoginUserNameDisplay) as TextView
        txtAfterLoginNameDisplay = findViewById(R.id.txtAfterLoginNameDisplay) as TextView
        txtAfterLoginPhoneDisplay = findViewById(R.id.txtAfterLoginPhoneDisplay) as TextView
        txtAfterLoginBirthdayDisplay = findViewById(R.id.txtAfterLoginBirthdayDisplay) as TextView
        txtAfterLoginEmail = findViewById(R.id.txtAfterLoginEmail) as TextView
        txtAfterLoginPassword = findViewById(R.id.txtAfterLoginPassword) as TextView
        txtAfterLoginGender = findViewById(R.id.txtAfterLoginGender) as TextView
        txtAfterLoginCountry = findViewById(R.id.txtAfterLoginCountry) as TextView
        txtAgree = findViewById(R.id.txtAgree) as TextView
    }

}
