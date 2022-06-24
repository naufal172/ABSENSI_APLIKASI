package org.d3if0128.absensi_aplikasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import org.d3if0128.absensi_aplikasi.databinding.ActivityAbsensiBinding

class AbsensiKeluar : AppCompatActivity() {
    private lateinit var binding: ActivityAbsensiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            saveData()
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
}