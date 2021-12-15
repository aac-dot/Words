package com.android.developer.words

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.developer.words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Vincula o recyclerView do layout com o recyclerView da activity
        recyclerView = binding.recyclerView

        // Define o LinearLayout para a recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Define qual será o adapter da recyclerView
        recyclerView.adapter = LetterAdapter()
    }
}