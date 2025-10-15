package com.cibertec.dam_t2_villegas.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cibertec.dam_t2_villegas.R
import com.cibertec.dam_t2_villegas.model.Reminder

class ReminderAdapter (
    private var reminders: List<Reminder>,
    private val onDeleteClick: (Reminder) -> Unit,
    private val onEditClick: (Reminder) -> Unit
) : RecyclerView.Adapter<ReminderAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtProductDesc: TextView = itemView.findViewById(R.id.txtProductDesc)
        val txtProductInfo: TextView = itemView.findViewById(R.id.txtProductInfo)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDelete)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_reminder_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = reminders[position]
        holder.txtProductDesc.text = product.title
        holder.txtProductInfo.text = product.description

        holder.btnDelete.setOnClickListener {
            onDeleteClick(product)
        }
        holder.btnEdit.setOnClickListener {
            onEditClick(product)
        }
    }

    override fun getItemCount(): Int = reminders.size
}