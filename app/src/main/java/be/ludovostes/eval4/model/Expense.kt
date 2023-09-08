package be.ludovostes.eval4.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// 4. Table Expense
@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    var expenseId: Long = 0,
    var name: String,
    var date: String,
    var value: Float?
)
