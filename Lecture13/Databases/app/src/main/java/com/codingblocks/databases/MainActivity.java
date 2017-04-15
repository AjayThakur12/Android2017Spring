package com.codingblocks.databases;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.codingblocks.databases.db.MyDatabaseHelper;
import com.codingblocks.databases.db.tables.TodoTable;
import com.codingblocks.databases.models.Todo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTodos;
    EditText etNewTodo;
    Button btnAddTodo;
    TodoListAdapter todoListAdapter;
    SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTodos = (ListView) findViewById(R.id.lvTodos);
        etNewTodo = (EditText) findViewById(R.id.etNewTodo);
        btnAddTodo = (Button) findViewById(R.id.btnAddTodo);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        mydb = dbHelper.getWritableDatabase();

        todoListAdapter = new TodoListAdapter(this);
        refreshTodos();


        lvTodos.setAdapter(todoListAdapter);

        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Todo newTodo = new Todo(
                        etNewTodo.getText().toString(),
                        false
                );
                TodoTable.addTask(mydb, newTodo);
                refreshTodos();

            }
        });

    }

    void setTodoDone (Todo todoToSetDone) {
        TodoTable.setDone(mydb, todoToSetDone);
        refreshTodos();
    }

    void refreshTodos () {
        todoListAdapter.updateTodos(
                TodoTable.fetchTodos(mydb)
        );
    }
}
