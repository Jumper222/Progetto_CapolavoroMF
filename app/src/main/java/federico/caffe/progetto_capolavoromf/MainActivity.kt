package federico.caffe.progetto_capolavoromf

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import federico.caffe.progetto_capolavoromf.databinding.ActivityMainBinding
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
            finishAffinity() // Chiude l'activity corrente e tutte le altre activity appartenenti alla stessa task
        }





        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun callActivity() {


        val intent = Intent(this,Seconda_pagina::class.java).also {

            startActivity(it)
        }
    }
}