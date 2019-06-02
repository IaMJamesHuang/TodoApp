package com.kt.james.todoapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import co.moonmonkeylabs.realmrecyclerview.RealmRecyclerView
import io.realm.Realm

/**
 *   author: James
 *   2019/6/2 9:46
 *   version: 1.0
 */

class TodosFragment: Fragment(), TodosAdapter.TodoItemClickListener {

    @BindView(R.id.todos_recycle_view)
    lateinit var recycleView: RecyclerView

    private val realm: Realm = Realm.getDefaultInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_todos, container, false)
        ButterKnife.bind(this, view)
        val todos = realm.where(Todo::class.java).findAll()
        val adapter = TodosAdapter(context!!, todos, this)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }

    override fun onClick(caller: View?, todo: Todo?) {
        val homeActivity = activity
        (homeActivity as HomeActivity).hideFab()

        if (todo != null) {
            val todoEditFragment = TodoEditFragment.newInstance(todo.id)
            homeActivity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, todoEditFragment, todoEditFragment::class.java.simpleName)
                .addToBackStack(todoEditFragment.javaClass.simpleName)
                .commit()
        }
    }

}