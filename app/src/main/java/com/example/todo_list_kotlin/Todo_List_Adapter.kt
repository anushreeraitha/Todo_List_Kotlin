package com.example.todo_list_kotlin

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_todo.view.*

class Todo_List_Adapter(
    private val todos : MutableList<Todos>
) : RecyclerView.Adapter<Todo_List_Adapter.TodosHolder>() {

    class TodosHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodosHolder {
        return TodosHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.items_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodosHolder, position: Int) {
        val curTodos = todos[position]
        holder.itemView.apply {
            item_todo_title.text = curTodos.todos_title
            check_box.isChecked = curTodos.isChecked
            toggleStrikeThrough(item_todo_title , curTodos.isChecked)
            check_box.setOnCheckedChangeListener { _, isChecked ->
                curTodos.isChecked = !curTodos.isChecked
                toggleStrikeThrough(item_todo_title , curTodos.isChecked)
            }

        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo(todo: Todos) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
       todos.removeAll { todo ->
           todo.isChecked
       }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if(isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}
