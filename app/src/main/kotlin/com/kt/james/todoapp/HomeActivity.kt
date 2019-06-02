package com.kt.james.todoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        val todosFragment = TodosFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, todosFragment, todosFragment.javaClass.simpleName)
            .addToBackStack(todosFragment.javaClass.simpleName)
            .commit()

        fab.setOnClickListener {
            val todoEditFragment = TodoEditFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, todoEditFragment, todoEditFragment.javaClass.simpleName)
                .addToBackStack(todoEditFragment.javaClass.simpleName)
                .commit()

            hideFab()
        }
    }

    @SuppressLint("RestrictedApi")
    fun hideFab() {
        fab.visibility = View.GONE
    }

    @SuppressLint("RestrictedApi")
    fun showFab() {
        fab.visibility = View.VISIBLE
    }

}
