package com.example.shadhin.helloworldkotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import cn.pedant.SweetAlert.SweetAlertDialog
import com.wajahatkarim3.easyvalidation.core.view_ktx.validator
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and


class RestApiCall : AppCompatActivity() {
    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
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

            var checkV1 = etPassword!!.validator()
                .nonEmpty()
                .minLength(8)
                .atleastOneNumber()
                .addErrorCallback {
                    SweetAlertDialog(this@RestApiCall, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error.").setContentText("Password " + it)
                        .setConfirmClickListener { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                        .show()
                }
                .addSuccessCallback {
                }
                .check()


            var checkV2 = etUserName!!.validator()
                .nonEmpty()
                .minLength(8)
                .atleastOneNumber()
                .addErrorCallback {
                    SweetAlertDialog(this@RestApiCall, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Sorry.").setContentText("User name " + it)
                        .setConfirmClickListener { sweetAlertDialog -> sweetAlertDialog.dismiss() }
                        .show()
                }
                .addSuccessCallback {

                }
                .check()

            if (checkV1 && checkV2) {
                val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
                intent.putExtra("etxtUserName", convertToSHA512(etUserName!!.text.toString()))
                intent.putExtra("etxtPassword", convertToSHA512(etPassword!!.text.toString()))
                // doApiVollyCall()
                sendcall()
                startActivity(intent)
            }
        }
        btnSubmitSHA2!!.setOnClickListener {
            val intent = Intent(this@RestApiCall, RegApiSuccess::class.java)
            intent.putExtra("etxtUserName", convertToSHA512(etUserName!!.text.toString()))
            intent.putExtra("etxtPassword", convertToSHA512(etPassword!!.text.toString()))

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
        val pass = etPassword?.text.toString()
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
                params.put("uname", name)
                params.put("pass", pass)
                return params
            }

        }
        mRequestQueue!!.add(mStringRequest!!)
    }

    fun convertToSHA512(input: String): String {
        try {
            // getInstance() method is called with algorithm SHA-512
            val md = MessageDigest.getInstance("SHA-512")

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            val messageDigest = md.digest(input.toByteArray())

            // Convert byte array into signum representation
            val no = BigInteger(1, messageDigest)

            // Convert message digest into hex value
            var hashtext = no.toString(16)

            // Add preceding 0s to make it 32 bit
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }

            // return the HashText
            return hashtext
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
        // For specifying wrong message digest algorithms
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
