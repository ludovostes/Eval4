package be.ludovostes.eval4.database

import androidx.room.Database
import androidx.room.RoomDatabase
import be.ludovostes.eval4.dao.ExpenseDao
import be.ludovostes.eval4.dao.ExpenseTypeDao
import be.ludovostes.eval4.model.Expense
import be.ludovostes.eval4.model.ExpenseType
import be.ludovostes.eval4.model.ExpenseTypeExpense

// 3. Mettre en place la base de données
@Database(entities = [Expense::class, ExpenseType::class],version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun expenseTypeDao(): ExpenseTypeDao
    abstract fun expenseDao(): ExpenseDao
}
