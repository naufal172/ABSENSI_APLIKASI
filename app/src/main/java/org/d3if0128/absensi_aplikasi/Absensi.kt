package org.d3if0128.absensi_aplikasi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.firestore.FirebaseFirestore
import org.d3if0128.absensi_aplikasi.databinding.ActivityAbsensiBinding


class Absensi : AppCompatActivity() {
    private lateinit var binding: ActivityAbsensiBinding
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private val CAMERA_PERMISSION_CODE = 123


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAbsensiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageSelfie.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
        }

        binding.saveButton.setOnClickListener {
            saveData()
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        }


        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //ompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener {

            }

        binding.inputLokasi.setOnClickListener {
            val intent = Intent(this@Absensi, LokasiActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {

        val db = FirebaseFirestore.getInstance()

        val absen: MutableMap<String, Any> = HashMap()
        absen["nama"] = binding.inputNama.text.toString()
        absen["keterangan"] = binding.inputKeterangan.text.toString()
        absen["jabatan"] = binding.inputJabatan.text.toString()
        absen["divisi"] = binding.inputaDivisi.text.toString()


        db.collection("karyawan")
            .add(absen)
            .addOnSuccessListener {
                Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkPermission(permissions: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@Absensi,
                permissions
            ) == PackageManager.PERMISSION_DENIED
        ) {


            ActivityCompat.requestPermissions(this@Absensi, arrayOf(permissions), requestCode)
        } else {
            Toast.makeText(this@Absensi, "Permission Granted already", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                Toast.makeText(this@Absensi, "Camera Permission Granted", Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(this@Absensi, "Camera Permission Denied", Toast.LENGTH_SHORT)
        }
    }

}
