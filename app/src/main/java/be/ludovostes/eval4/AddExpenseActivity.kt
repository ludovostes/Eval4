package be.ludovostes.eval4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import be.ludovostes.eval4.R
import be.ludovostes.eval4.database.DataBase
import be.ludovostes.eval4.databinding.ActivityAddExpenseBinding
import be.ludovostes.eval4.model.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// 15.
class AddExpenseActivity : AppCompatActivity() {

    // 15.2
    lateinit var db: DataBase

    private lateinit var binding: ActivityAddExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExpenseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 16.
        db = Room.databaseBuilder(this, DataBase::class.java, "Sample.db").build()

        // 17.1 BTN
        val saveBtn = binding.addButton
        saveBtn.setOnClickListener {
            if (!binding.editNameText.text.toString()
                    .isEmpty() && !binding.editDateText.text.toString()
                    .isEmpty() && !binding.editValueText.text.toString().isEmpty()
            ) {
                addExpense()
                showAlertDialog("${binding.editNameText.text} enregistré !")
            } else {
                showAlertDialog("Un champ est vide")
            }
        }

        // 18. Back btn
        val backBtn = binding.backButton
        backBtn.setOnClickListener {
            finish()
        }
    }

    // Coroutine
    private fun addExpense() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                add()
            } catch (e: Exception) {
                println("Exception : $e")
            }
        }
    }

    // 17.2
    private fun showAlertDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Message")
        builder.setMessage(message)

        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss() // Ferme l'alerte
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }

    // 15.2
    suspend fun add() {
        withContext(Dispatchers.Default) {
            val name = binding.editNameText.text.toString()
            val date = binding.editDateText.text.toString()
            val value = binding.editValueText.text.toString().toFloatOrNull()
            val expenseDao = db.expenseDao()
            var expenses = expenseDao.getAll()

            expenseDao.insert(
                Expense(
                    name = name,
                    date = date,
                    value = value
                )
            )
        }
    }
}