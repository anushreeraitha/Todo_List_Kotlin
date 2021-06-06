package com.example.todo_list_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todo_adapter : Todo_List_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todo_adapter = Todo_List_Adapter(mutableListOf())
        rvtodo_list.adapter = todo_adapter

        add_button.setOnClickListener {
            val todo_title = add_todo_text.text.toString()

            if(todo_title.isNotEmpty()) {
                val txt_title = Todos(todo_title)
                todo_adapter.addTodo(txt_title)
                add_todo_text.text.clear()
            }
        }

        delete_button.setOnClickListener {
            todo_adapter.deleteDoneTodos()
        }
    }
}