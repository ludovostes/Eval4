package be.ludovostes.eval4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.ludovostes.eval4.model.ExpenseType

// 2. Communication entre la table et le code
// détermine les paramètres possibles.
@Dao
interface ExpenseTypeDao {
    @Query("SELECT * FROM ExpenseType") // permet de retourner les données
    fun getAll(): List<ExpenseType>
    // ?
    @Query("SELECT * FROM ExpenseType WHERE id IN (:expenseTypeIds)")
    fun loadAllByIds(expenseTypeIds: IntArray): List<ExpenseType>
    // ?
    @Query("SELECT * FROM ExpenseType WHERE name_Expense_Type LIKE :nameExpenseType LIMIT 1")
    fun findByName(nameExpenseType: String): ExpenseType
    @Insert(onConflict = OnConflictStrategy .REPLACE)
    fun insert(expenseType: ExpenseType)
    @Insert
    fun insertAll(vararg expensesType: ExpenseType)
    @Delete
    fun delete(expenseType: ExpenseType)
}