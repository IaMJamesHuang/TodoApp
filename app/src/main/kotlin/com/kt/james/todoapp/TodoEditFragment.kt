package com.kt.james.todoapp

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import io.realm.Realm
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.UI
import java.util.*

/**
 *   author: James
 *   2019/6/1 16:23
 *   version: 1.0
 */

class TodoEditFragment : Fragment() {

    private val realm: Realm = Realm.getDefaultInstance()
    private var todo: Todo? = null

    private var title: EditText? = null
    private var content: EditText? = null
    private var add: Button? = null

    companion object {
        const val TODO_ID_KEY: String = "todo_id_key"

        fun newInstance(id: String): TodoEditFragment {
            val args = Bundle()
            args.putString(TODO_ID_KEY, id)
            val todoEditFragment: TodoEditFragment = newInstance()
            todoEditFragment.arguments = args
            return todoEditFragment
        }

        fun newInstance(): TodoEditFragment {
            return TodoEditFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return UI {
            verticalLayout {
                padding = dip(30)
                title = editText {
                    hint = "标题"
                    id = R.id.todo_title
                }
                content = editText {
                    hint = "代办内容"
                    height = 400
                }
                add = button {
                    text = "添加"
                    textColor = Color.WHITE
                    setBackgroundColor(Color.DKGRAY)
                    onClick {
                        createTodoFrom(title, content)
                    }
                }
            }
        }.view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val args = arguments
        if (args != null && args.containsKey(TODO_ID_KEY)) {
           val todoId = args.getString(TODO_ID_KEY)
            todo = realm.where(Todo::class.java).equalTo("id", todoId).findFirst()

            title?.setText(todo?.title)
            content?.setText(todo?.content)

            add?.setText("保存")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
        (activity as HomeActivity).showFab()
    }

    private fun createTodoFrom(title: EditText?, content: EditText?) {
        realm.beginTransaction()
        val t = todo ?: realm.createObject(Todo::class.java, UUID.randomUUID().toString())
        t.title = title?.text.toString()
        t.content = content?.text.toString()
        realm.commitTransaction()
        activity?.supportFragmentManager?.popBackStack()
    }

}