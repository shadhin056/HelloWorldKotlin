package com.example.shadhin.helloworldkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    //init variable
    private var etxtUserName: EditText? = null
    private var etxtNickName: EditText? = null
    private var etxtPhoneNumer: EditText? = null
    private var etxtBirthday: EditText? = null
    private var etxtEmail: EditText? = null
    private var etxtPassword: EditText? = null
    private var etxtReEnterPassword: EditText? = null
    private var spPresentCountry: Spinner? = null
    private var chkAgree: CheckBox? = null
    private var raGender: RadioGroup? = null
    private var btnLogin: Button? = null
    private var btnReg: Button? = null
    private var btnRandomInput: Button? = null
    private var btnReset: Button? = null

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
            intent.putExtra("etxtPhoneNumer", etxtPhoneNumer!!.text.toString())
            intent.putExtra("etxtBirthday", etxtBirthday!!.text.toString())
            intent.putExtra("etxtEmail", etxtEmail!!.text.toString())
            intent.putExtra("etxtPassword", etxtPassword!!.text.toString())
            //intent.putExtra("spPresentCountry", spPresentCountry!!.text.toString())
            //intent.putExtra("chkAgree", chkAgree!!.text.toString())
            //intent.putExtra("raGender", raGender!!.text.toString())
            startActivity(intent)
        }
        btnRandomInput!!.setOnClickListener {
            etxtUserName!!.setText("shadhin")
            etxtNickName!!.setText("Moniruzzaman")
            etxtPhoneNumer!!.setText("01672708329")
            etxtBirthday!!.setText("31/12/1990")
            etxtEmail!!.setText("shadhinemail@gmail.com")
            etxtPassword!!.setText("123456")
            etxtReEnterPassword!!.setText("123456")
        }

        btnReset!!.setOnClickListener {
            etxtUserName!!.setText("")
            etxtNickName!!.setText("")
            etxtPhoneNumer!!.setText("")
            etxtBirthday!!.setText("")
            etxtEmail!!.setText("")
            etxtPassword!!.setText("")
            etxtReEnterPassword!!.setText("")
        }
    }
    private fun initView() {
        etxtUserName = findViewById(R.id.etxtUserName) as EditText
        etxtNickName = findViewById(R.id.etxtNickName) as EditText
        etxtPhoneNumer = findViewById(R.id.etxtPhoneNumer) as EditText
        etxtBirthday = findViewById(R.id.etxtBirthday) as EditText
        etxtEmail = findViewById(R.id.etxtEmail) as EditText
        etxtPassword = findViewById(R.id.etxtPassword) as EditText
        etxtReEnterPassword = findViewById(R.id.etxtReEnterPassword) as EditText
        spPresentCountry = findViewById(R.id.spPresentCountry) as Spinner
        chkAgree = findViewById(R.id.chkAgree) as CheckBox
        raGender = findViewById(R.id.raGender) as RadioGroup
        btnLogin = findViewById(R.id.btnLogin) as Button
        btnReg = findViewById(R.id.btnReg) as Button
        btnRandomInput = findViewById(R.id.btnRandomInput) as Button
        btnReset = findViewById(R.id.btnReset) as Button
    }
}
