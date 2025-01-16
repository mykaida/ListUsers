package com.example.listusers

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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
    private lateinit var toolbar: Toolbar
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
        toolbar = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbar)
        title = getString(R.string.katalog_users)
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
        usersLV.onItemClickListener = MyDialog.createAlertDialog(this, adapter)

            }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.exitMN ->finish()
            else -> return super.onOptionsItemSelected(item)
        }
    return super.onOptionsItemSelected(item)
    }

}
