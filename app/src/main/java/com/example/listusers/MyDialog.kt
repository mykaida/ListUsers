package com.example.listusers

import android.app.Dialog
import android.content.Context
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog {

    companion object{
        fun createAlertDialog(context: Context, adapter: ArrayAdapter<User>) =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val builder = AlertDialog.Builder(context)
                val note = adapter.getItem(position)
                builder.setTitle("Важно!!!")
                    .setMessage("Удалить пользователя $note?")
                    .setNegativeButton("Нет"){ dialog, wich ->
                        dialog.cancel()
                    }
                    .setPositiveButton("Удалить"){dialog, wich ->
                        adapter.remove(note)
                        Toast.makeText(context, "Такой нам не нужен - $note!", Toast.LENGTH_LONG).show()
                    }.create()
                builder.show()
            }

    }
}