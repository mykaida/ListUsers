package com.example.listusers

import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val users:MutableList<User> = mutableListOf()

    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var saveBTN:Button
    private lateinit var usersLV: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nameET = findViewById(R.id.nameET)
        ageET = findViewById(R.id.ageET)
        saveBTN = findViewById(R.id.saveBTN)
        usersLV = findViewById(R.id.usersLV)
        val adapter = ArrayAdapter<User>(this,android.R.layout.
        simple_expandable_list_item_1,users)
        usersLV.adapter = adapter

        saveBTN.setOnClickListener {
            var ageInt = 0
            if(nameET.text.isEmpty()) {
                Toast.makeText(this,"Имя где?",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            try {
                ageInt = ageET.text.toString().toInt()
            } catch (e:Exception){
                Toast.makeText(this,"Возраст что?",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if(ageInt <= 0){
                Toast.makeText(this,"Возраст что?",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            users.add(User(nameET.text.toString(),ageInt))
            adapter.notifyDataSetChanged()
            nameET.text.clear()
            ageET.text.clear()
        }
        usersLV.onItemClickListener =
            AdapterView.OnItemClickListener { parent, v, position, id ->
                val note = adapter.getItem(position)
                adapter.remove(note)
                Toast.makeText(this, "Такой нам не нужен: $note", Toast.LENGTH_LONG).show()

            }

    }
}

data class User(val name:String, val age:Int){
    override fun toString(): String {
        return "Имя: $name  Возраст: $age"
    }
}