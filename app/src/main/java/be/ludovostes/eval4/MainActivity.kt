package be.ludovostes.eval4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import be.ludovostes.eval4.database.DataBase
import be.ludovostes.eval4.databinding.ActivityMainBinding
import be.ludovostes.eval4.model.Expense
import be.ludovostes.eval4.model.ExpenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // Binding
    private lateinit var binding: ActivityMainBinding

    // 8.1 Hérite de DataBase
    lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 8.2 Creation de la db à l'aide de room
        db = Room.databaseBuilder(this, DataBase::class.java, "Sample.db").build()

        // 9.3
        getExpense()

        // Add btn
        val addButton = binding.addButton
        addButton.setOnClickListener {
            // Créer une intent pour démarrer l'activité
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    // 18.
    override fun onResume() {
        super.onResume()
        // Chargez les données de la base de données à chaque fois que l'activité redevient visible
        getExpense()
    }

    // 9.1 Coroutine
    private fun getExpense() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                dbActions()
            } catch (e: Exception) {
                println("Exception : $e")
            }
        }
    }

    // 9.2 Instancie toutes les actions dans la DB
    // Toute action doit etre placée dans une kouroutine
    suspend fun dbActions() {
        withContext(Dispatchers.Default) {
            val expenseTypeDao = db.expenseTypeDao() // je récupère toutes les méthodes dao dans une variable
            val expenseDao = db.expenseDao()

            var expenses = expenseTypeDao.getAll()
            Log.d("TEST", "expense count : ${expenses.size}")

            // 13.2
            // S'assurer d'abord que "expenses" contient les données avant de mettre à jour l'UI
            runOnUiThread {
                setupRecyclerView(expenses)
            }
            expenseTypeDao.insert(ExpenseType(id = 1, nameExpenseType = "Taxe"))
            //expenseDao.insert(Expense(name = "frite", date = "10", value =))
        }
    }

    // 13.1
    private fun setupRecyclerView(expenseList: List<ExpenseType>) {
        // Après avoir obtenu les données de la base de données
        val recyclerView = binding.recyclerView
        val adapter = TypeAdapter(expenseList)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter
    }
}