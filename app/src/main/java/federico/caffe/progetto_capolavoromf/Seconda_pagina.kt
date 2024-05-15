package federico.caffe.progetto_capolavoromf

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import federico.caffe.progetto_capolavoromf.databinding.ActivitySecondaPaginaBinding
import federico.caffe.progetto_capolavoromf.DomandeDao
import android.widget.Toast

class Seconda_pagina : AppCompatActivity() {

    private lateinit var binding: ActivitySecondaPaginaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondaPaginaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val db = DomandeDatabase.getDatabase(applicationContext)
        val domandeQuestionDao = db.domandeDao()

        // Carica la prima domanda
        caricaNuovaDomanda(domandeQuestionDao)
    }

    private fun caricaNuovaDomanda(domandeQuestionDao: DomandeDao) {
        Thread {
            val question = domandeQuestionDao.getRandomQuestion()
            runOnUiThread {
                // Collega i dati ai bottoni e alla TextView della domanda
                binding.domanda.text = question.domanda
                binding.risposta1.text = question.risposta1
                binding.risposta2.text = question.risposta2
                binding.risposta3.text = question.risposta3
                binding.risposta4.text = question.risposta4

                // Imposta il listener per controllare la risposta
                binding.risposta1.setOnClickListener { checkAnswer(1, question.trueAns, domandeQuestionDao) }
                binding.risposta2.setOnClickListener { checkAnswer(2, question.trueAns, domandeQuestionDao) }
                binding.risposta3.setOnClickListener { checkAnswer(3, question.trueAns, domandeQuestionDao) }
                binding.risposta4.setOnClickListener { checkAnswer(4, question.trueAns, domandeQuestionDao) }
            }
        }.start()
    }

    private fun checkAnswer(rispostaSelezionata: Int, rispostaCorretta: Int, domandeQuestionDao: DomandeDao) {
        if (rispostaSelezionata == rispostaCorretta) {
            // Risposta corretta
            Toast.makeText(this, "Risposta corretta!", Toast.LENGTH_SHORT).show()
        } else {
            // Risposta sbagliata
            Toast.makeText(this, "Risposta sbagliata!", Toast.LENGTH_SHORT).show()
        }
        // Carica una nuova domanda
        caricaNuovaDomanda(domandeQuestionDao)
    }
}
