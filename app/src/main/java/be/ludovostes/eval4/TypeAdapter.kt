package be.ludovostes.eval4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.ludovostes.eval4.model.ExpenseType

// 11.1
class TypeAdapter(private val expenses: List<ExpenseType>) : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // 11.2 Ajouter un customcell item_book.xml
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val expenses = expenses[position]
        holder.nameTextView.text = expenses.nameExpenseType
        holder.valueTextView.text = expenses.nameExpenseType
        holder.dateTextView.text = expenses.nameExpenseType
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}