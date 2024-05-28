package federico.caffe.progetto_capolavoromf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import federico.caffe.progetto_capolavoromf.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //Qua creiamo la classe di binding NomeFile.xml -> NomeFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            DomandeDatabase::class.java, "database-name"
        ).build()


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //NON SO CHE FACCIA STA ROBA PERO E NECESSARIA

        binding.ButtonPlay.setOnClickListener {
            Log.d("MainActivity", "CLICCATO!")
            binding.textView.text = "Si parte!"

            callActivity()

        } //Crea l' evento OnClick sull' elemento ID: ButtonPlay, Stampa un log e cambia il text di textView

        binding.buttonCloseApp.setOnClickListener {

            finish() // Chiude l'activity corrente e ritorna all'activity precedente o chiude l'applicazione se non ci sono altre activity nello stack
        }





        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun callActivity() {


        val intent = Intent(this, Seconda_pagina::class.java).also {

            startActivity(it)
        }
    }
}