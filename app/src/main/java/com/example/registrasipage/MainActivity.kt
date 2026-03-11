package com.example.registrasipage

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inisialisasi findViewById
        val inputNama = findViewById<EditText>(R.id.inputNama)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val inputPass = findViewById<EditText>(R.id.inputPassword)
        val inputConfirm = findViewById<EditText>(R.id.inputConfirmPassword)
        val radioGroup = findViewById<RadioGroup>(R.id.rgGender)
        val cbgame = findViewById<CheckBox>(R.id.cbGame)
        val cbNyanyi = findViewById<CheckBox>(R.id.cbNyanyi)
        val cbNonton = findViewById<CheckBox>(R.id.cbNonton)
        val spinner = findViewById<Spinner>(R.id.spinnerAsal)
        val btnDaftar = findViewById<Button>(R.id.btnRegister)

        // spinner
        val dataAsal = arrayOf("Jakarta", "Bandung", "Bogor")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dataAsal)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // validasi btnDaftar
        btnDaftar.setOnClickListener {
            val nama = inputNama.text.toString()
            val email = inputEmail.text.toString()
            val pass = inputPass.text.toString()
            val confirm = inputConfirm.text.toString()

            // validasi input
            if (nama.isEmpty()) {
                inputNama.error = "Nama tidak boleh kosong"
            } else if (!email.contains("@")) {
                inputEmail.error = "Format email salah"
            } else if (pass != confirm) {
                inputConfirm.error = "Password tidak cocok"
            } else if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih Gender dulu!", Toast.LENGTH_SHORT).show()
            } else {
                // konfirmasi
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Konfirmasi")
                builder.setMessage("Apakah Anda yakin ingin mendaftar?")
                builder.setPositiveButton("Ya") { _, _ ->
                    Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Tidak", null)
                builder.show()
            }
        }

        // --- gesture long click
        btnDaftar.setOnLongClickListener {
            Toast.makeText(this, "Aksi Tambahan: Form Dikosongkan!", Toast.LENGTH_SHORT).show()
            // reset form
            inputNama.text.clear()
            inputEmail.text.clear()
            inputPass.text.clear()
            inputConfirm.text.clear()
            radioGroup.clearCheck()

            // reset radio grup
            radioGroup.clearCheck()
            cbgame.isChecked = false
            cbNyanyi.isChecked = false
            cbNonton.isChecked = false
            true
        }
    }
}