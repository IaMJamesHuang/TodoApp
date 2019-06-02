package com.kt.james.todoapp

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import io.realm.RealmBasedRecyclerViewAdapter
import io.realm.RealmResults
import io.realm.RealmViewHolder

/**
 *   author: James
 *   2019/6/2 9:47
 *   version: 1.0
 */

@Deprecated("Realm-RecycleView与Realm版本不兼容")
class TodoAdapter(context: Context, result: RealmResults<Todo>, bool: Boolean, bool2: Boolean, private val onClickListener: TodoItemClickListener)
    : RealmBasedRecyclerViewAdapter<Todo, TodoAdapter.ViewHolder>(context, result, bool, bool2) {

    override fun onCreateRealmViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = inflater.inflate(R.layout.item_todo, p0, false)
        return ViewHolder(v, onClickListener)
    }

    override fun onBindRealmViewHolder(p0: ViewHolder, p1: Int) {
        val todo = realmResults[p1]

        p0.todoTitle.setText(todo?.title)
        p0.todoContent.setText(todo?.content)
    }

    inner class ViewHolder(view: View, private val clickListener: TodoItemClickListener?): RealmViewHolder(view), View.OnClickListener {

        @BindView(R.id.todo_item_title)
        lateinit var todoTitle: TextView

        @BindView(R.id.todo_item_content)
        lateinit var todoContent: TextView

        init {
            ButterKnife.bind(this, view)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(v, realmResults[adapterPosition])
        }

    }

    interface TodoItemClickListener {
        fun onClick(caller: View?, todo: Todo?)
    }

}