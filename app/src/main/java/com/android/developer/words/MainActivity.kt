package com.android.developer.words

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.developer.words.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Vincula o recyclerView do layout com o recyclerView da activity
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    private fun chooseLayout() {
        // Define o tipo de gerenciador de layout para a recyclerView
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }

        // Define o adapter LetterAdapter para recyclerView
        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem : MenuItem?) {
        if (menuItem == null) {
            return
        }
        menuItem.icon = if (isLinearLayoutManager)
            ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
        else ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_menu)

        setIcon(layoutButton)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_menu -> {

                isLinearLayoutManager = !isLinearLayoutManager

                chooseLayout()
                setIcon(item)

                return true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}