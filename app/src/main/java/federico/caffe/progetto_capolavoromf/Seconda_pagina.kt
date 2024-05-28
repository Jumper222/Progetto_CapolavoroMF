package federico.caffe.progetto_capolavoromf

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import federico.caffe.progetto_capolavoromf.databinding.ActivitySecondaPaginaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Seconda_pagina : AppCompatActivity() {
    var punteggio = 0
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
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val question = domandeQuestionDao.getRandomQuestion()
                if (question != null) {
                    withContext(Dispatchers.Main) {
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
                        binding.indietro.setOnClickListener { exit() }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Seconda_pagina, "Nessuna domanda disponibile.", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Seconda_pagina", "Errore nel caricamento della domanda", e)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Seconda_pagina, "Errore nel caricamento della domanda.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun checkAnswer(rispostaSelezionata: Int, rispostaCorretta: Int, domandeQuestionDao: DomandeDao) {
        if (rispostaSelezionata == rispostaCorretta) {
            // Risposta corretta
            Toast.makeText(this, "Risposta corretta!", Toast.LENGTH_SHORT).show()
            punteggio++
        } else {
            // Risposta sbagliata
            Toast.makeText(this, "Risposta sbagliata!", Toast.LENGTH_SHORT).show()
            punteggio--
            if (punteggio < 0) punteggio = 0
        }
        binding.Score.text = "Score: $punteggio"
        caricaNuovaDomanda(domandeQuestionDao)
    }

    private fun exit() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
