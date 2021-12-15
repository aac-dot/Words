package com.android.developer.words

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter para [RecyclerView] em [DetailActivity]
 * */
class WordAdapter(private val letterID : String, context : Context) : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    private val filteredWords : List<String>

    init {
        // Recupera a lista de palavras do res/values/arrays.xml
        val words = context.resources.getStringArray(R.array.words).toList()

        // Retorna os items da coleção se a condição é true,
        // nesse caso se um item começar com uma determinada letra, ignorando letras MAIUSCULA OU minuscula.
        filteredWords = words.filter { it.startsWith(letterID, ignoreCase = true) }
            // Embalha a lista de palavras recuperadas.
            .shuffled()
            // Retorna os primeiros n items com uma lista, nesse caso os 5 primeiros.
            .take(5)
            // Retorna um versão ordenada da 5 cinco primeiras letras.
            .sorted()
    }

    class WordViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.button_item)
    }

    // Cria novas view com o layout item_view como modelo.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        layout.accessibilityDelegate = Accessibility

        return WordViewHolder(layout)
    }

    // Substitui o contento de uma view existente com novos dados.
    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val item = filteredWords[position]
        val context = holder.view.context

        holder.button.text = item
        holder.button.setOnClickListener {
            // Cria o formato da url usada para a pesquisa.
            val queryUrl : Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredWords.size
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            super.onInitializeAccessibilityNodeInfo(host, info)

            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }



}