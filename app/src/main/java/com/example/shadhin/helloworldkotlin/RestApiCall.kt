package com.example.shadhin.helloworldkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.VolleyLog.e
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.util.HashMap
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import com.android.volley.RequestQueue



class RestApiCall : AppCompatActivity() {

    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
    private var btnSubmitMOD5: Button? = null
    private var btnSubmitSHA2: Button? = null
    val jsonobj=JSONObject();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_api_call)
        initView()
        define()
    }

    private fun define() {

        btnSubmitMOD5!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", etUserName!!.text.toString())
            intent.putExtra("etxtPassword", etPassword!!.text.toString())

            doApiVollyCall()

            startActivity(intent)
        }
        btnSubmitSHA2!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", etUserName!!.text.toString())
            intent.putExtra("etxtPassword", etPassword!!.text.toString())

            doApiVollyCall()
            startActivity(intent)
        }
    }
    private fun initView() {

        etUserName = findViewById(R.id.etUserName) as EditText
        etPassword = findViewById(R.id.etPassword) as EditText
        btnSubmitMOD5 = findViewById(R.id.btnSubmitMOD5) as Button
        btnSubmitSHA2 = findViewById(R.id.btnSubmitSHA2) as Button
    }

    private fun doApiVollyCall() {//getting the record values
        val name = etUserName?.text.toString()
        val genre = etPassword?.text.toString()
        var url ="http://10.11.201.52:8084/TestWeb/MD5"
        //creating volley string request
        val stringRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("uname", name)
                params.put("pass", genre)
                return params
            }
        }
        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }}
