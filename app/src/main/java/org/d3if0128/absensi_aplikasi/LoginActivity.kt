package org.d3if0128.absensi_aplikasi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



class LoginActivity : AppCompatActivity() {
    lateinit var strNama: String
    lateinit var strPassword: String
    var REQ_PERMISSION = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_absensi)


    }


}