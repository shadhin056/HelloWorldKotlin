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
import android.provider.SyncStateContract.Helpers.update
import java.security.MessageDigest
import kotlin.experimental.and


class RestApiCall : AppCompatActivity() {

    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
    private var btnSubmitMOD5: Button? = null
    private var btnSubmitSHA2: Button? = null
    val jsonobj = JSONObject();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_api_call)
        initView()
        define()
    }

    private fun define() {

        btnSubmitMOD5!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", convertToMod5(etUserName!!.text.toString()))
            intent.putExtra("etxtPassword", convertToMod5(etPassword!!.text.toString()))

            //doApiVollyCall()
            sendcall()
            startActivity(intent)
        }
        btnSubmitSHA2!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", etUserName!!.text.toString())
            intent.putExtra("etxtPassword", etPassword!!.text.toString())

            // doApiVollyCall()
            sendcall()
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
        var url = "http://10.11.201.52:8084/TestWeb/MD5"
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
    }

    fun sendcall() {
        val name = etUserName?.text.toString()
        val genre = etPassword?.text.toString()
        //RequestQueue initialized
        var mRequestQueue = Volley.newRequestQueue(this)
        var url = "http://10.11.201.52:8084/TestWeb/MD5"
        //String Request initialized
        var mStringRequest = object : StringRequest(Method.POST, url, Response.Listener { response ->
            Toast.makeText(applicationContext, "Logged In Successfully", Toast.LENGTH_SHORT).show()


        }, Response.ErrorListener { error ->
            Log.i("This is the error", "Error :" + error.toString())
            Toast.makeText(
                applicationContext,
                "Please make sure you enter correct password and username",
                Toast.LENGTH_SHORT
            ).show()
        }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("uname", convertToMod5(name))
                params.put("pass", convertToMod5(genre))
                return params
            }

        }
        mRequestQueue!!.add(mStringRequest!!)
    }


    fun convertToMod5(password: String): String {

        val md = MessageDigest.getInstance("MD5")
        md.update(password.toByteArray())
        val byteData = md.digest()
        val sb = StringBuffer()
        for (i in byteData.indices)
            sb.append(Integer.toString((byteData[i] and 0xff.toByte()) + 0x100, 16).substring(1))
        // println("Digest(in hex format):: " + sb.toString())
        return sb.toString();
    }

}
