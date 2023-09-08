package be.ludovostes.eval4.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// 1. Présentation de la table
@Entity
data class ExpenseType(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, // pour que l'autocrémentation fonctionne
    @ColumnInfo(name = "name_Expense_Type") // ce qui s'affiche dans la db
    val nameExpenseType: String?, // variables
)
