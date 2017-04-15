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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todos = new ArrayList<>();
    ListView lvTodos;
    EditText etNewTodo;
    Button btnAddTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTodos = (ListView) findViewById(R.id.lvTodos);
        etNewTodo = (EditText) findViewById(R.id.etNewTodo);
        btnAddTodo = (Button) findViewById(R.id.btnAddTodo);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        final SQLiteDatabase mydb = dbHelper.getWritableDatabase();

        todos = TodoTable.fetchTodos(mydb);

        final ArrayAdapter<String> todoListAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                todos
        );

        lvTodos.setAdapter(todoListAdapter);

        btnAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todos.add(etNewTodo.getText().toString());
                todoListAdapter.notifyDataSetChanged();
                TodoTable.addTask(mydb, etNewTodo.getText().toString());
            }
        });

    }
}
