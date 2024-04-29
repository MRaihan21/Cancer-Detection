package com.dicoding.asclepius.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.R
import com.dicoding.asclepius.database.History
import java.text.NumberFormat

class HistoryAdapter(private var historyList: ArrayList<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val imgHistory: ImageView = item.findViewById(R.id.history_image)
        val nameHistory: TextView = item.findViewById(R.id.history_name_text)
        val scoreHistory: TextView = item.findViewById(R.id.history_score_text)
        val dateHistory: TextView = item.findViewById(R.id.history_date_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = historyList[position]
        holder.imgHistory.setImageURI(Uri.parse(history.uri))
        holder.nameHistory.text = holder.itemView.context.getString(R.string.name, history.label)
        holder.scoreHistory.text = holder.itemView.context.getString(R.string.score,
            NumberFormat.getPercentInstance().format(history.confidence).toString()
        )
        holder.dateHistory.text =
            holder.itemView.context.getString(R.string.date, history.dateGenerate)
    }
}