package be.ludovostes.eval4.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

// 6. Table de liaison (jointure)
@Entity(primaryKeys = ["id", "expenseId"])
data class ExpenseTypeExpense(
    var id: Long,
    var expenseId: Long
)

data class ExpenseTypeWithExpense(
    @Embedded
    val expenseType: ExpenseType,
    @Relation(
        parentColumn = "id",
        entityColumn = "expenseId",
        associateBy = Junction(ExpenseTypeExpense::class)
    )
    val genres: List<Expense>
)

data class ExpenseWithExpenseType(
    @Embedded
    val expense: Expense,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "id",
        associateBy = Junction(ExpenseTypeExpense::class)
    )
    val books: List<ExpenseType>
)
