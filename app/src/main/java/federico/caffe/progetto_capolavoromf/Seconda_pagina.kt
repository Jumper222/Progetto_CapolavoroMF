package federico.caffe.progetto_capolavoromf

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import federico.caffe.progetto_capolavoromf.databinding.ActivityMainBinding
import federico.caffe.progetto_capolavoromf.databinding.ActivitySecondaPaginaBinding

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

        val input = intent.getStringExtra("EXTRA_MESSAGE")

        val textView = binding.textView2.apply {
            text = input
        }
    }
}