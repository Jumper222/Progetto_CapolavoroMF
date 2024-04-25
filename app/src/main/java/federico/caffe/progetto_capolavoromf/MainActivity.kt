package federico.caffe.progetto_capolavoromf

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import federico.caffe.progetto_capolavoromf.databinding.ActivityMainBinding
import android.util.Log


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //Qua creiamo la classe di binding NomeFile.xml -> NomeFileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //NON SO CHE FACCIA STA ROBA PERO E NECESSARIA

        binding.ButtonPlay.setOnClickListener {
            Log.d("MainActivity", "CLICCATO!")
            binding.textView.text = "12344aZS1à+è3"

        } //Crea l' evento OnClick sull' elemento ID: ButtonPlay, Stampa un log e cambia il text di textView



        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}