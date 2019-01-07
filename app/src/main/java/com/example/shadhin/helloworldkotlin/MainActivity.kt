package com.example.shadhin.helloworldkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    //init variable
    private var etxtUserName: EditText? = null
    private var etxtNickName: EditText? = null
    private var etxPhoneNumer: EditText? = null
    private var etxBirthday: EditText? = null
    private var etxEmail: EditText? = null
    private var etxPassword: EditText? = null
    private var spPresentCountry: Spinner? = null
    private var chkAgree: CheckBox? = null
    private var raGender: RadioGroup? = null
    private var btnLogin: Button? = null
    private var btnReg: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        define()
    }
    private fun define() {

        btnReg!!.setOnClickListener {
            val intent = Intent(this@MainActivity, RegSuccess::class.java)
            intent.putExtra("etxtUserName", etxtUserName!!.text.toString())
            intent.putExtra("etxtNickName", etxtNickName!!.text.toString())
            intent.putExtra("etxPhoneNumer", etxPhoneNumer!!.text.toString())
            intent.putExtra("etxBirthday", etxBirthday!!.text.toString())
            intent.putExtra("etxEmail", etxEmail!!.text.toString())
            intent.putExtra("etxPassword", etxPassword!!.text.toString())
            //intent.putExtra("spPresentCountry", spPresentCountry!!.text.toString())
            //intent.putExtra("chkAgree", chkAgree!!.text.toString())
            //intent.putExtra("raGender", raGender!!.text.toString())
            startActivity(intent)
        }
    }
    private fun initView() {
        etxtUserName = findViewById(R.id.etxtUserName) as EditText
        etxtNickName = findViewById(R.id.etxtNickName) as EditText
        etxPhoneNumer = findViewById(R.id.etxPhoneNumer) as EditText
        etxBirthday = findViewById(R.id.etxBirthday) as EditText
        etxEmail = findViewById(R.id.etxEmail) as EditText
        etxPassword = findViewById(R.id.etxPassword) as EditText
        spPresentCountry = findViewById(R.id.spPresentCountry) as Spinner
        chkAgree = findViewById(R.id.chkAgree) as CheckBox
        raGender = findViewById(R.id.raGender) as RadioGroup
        btnLogin = findViewById(R.id.btnLogin) as Button
        btnReg = findViewById(R.id.btnReg) as Button
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
