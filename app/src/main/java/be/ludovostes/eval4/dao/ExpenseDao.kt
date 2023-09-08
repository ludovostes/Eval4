package be.ludovostes.eval4.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import be.ludovostes.eval4.model.Expense

// 5.
@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getAll(): List<Expense>
    @Query("SELECT * FROM Expense WHERE expenseId IN (:expenseIds)")
    fun loadAllByIds(expenseIds: IntArray): List<Expense>
    @Query("SELECT * FROM Expense WHERE expenseId LIKE :id LIMIT 1")
    fun getById(id: Long): Expense
    @Query("SELECT * FROM Expense WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): Expense
    @Insert(onConflict = OnConflictStrategy .REPLACE)
    fun insert(expense: Expense)
    @Insert
    fun insertAll(vararg expenses: Expense)
    @Delete
    fun delete(expense: Expense)
}