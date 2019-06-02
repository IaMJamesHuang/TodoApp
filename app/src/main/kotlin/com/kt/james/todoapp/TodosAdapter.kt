package com.kt.james.todoapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.realm.RealmResults
import org.jetbrains.anko.layoutInflater
import java.util.zip.Inflater

/**
 *   author: James
 *   2019/6/2 10:33
 *   version: 1.0
 */

class TodosAdapter(val context: Context, result: RealmResults<Todo>, val listener: TodoItemClickListener?) : RecyclerView.Adapter<TodosAdapter.ViewHolder>() {

    val list = mutableListOf<Todo>()

    init {
        list.addAll(result)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = context.layoutInflater.inflate(R.layout.item_todo, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val todo = list[p1]

        p0.todoTitle.setText(todo.title)
        p0.todoContent.setText(todo.content)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        @BindView(R.id.todo_item_title)
        lateinit var todoTitle: TextView

        @BindView(R.id.todo_item_content)
        lateinit var todoContent: TextView

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener?.onClick(v, list[adapterPosition])
        }
    }

    interface TodoItemClickListener {
        fun onClick(caller: View?, todo: Todo?)
    }

}