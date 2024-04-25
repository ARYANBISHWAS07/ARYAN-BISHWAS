package com.example.submission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText taskEntry;
    private Button addButton;
    private ListView taskListView;
    private Button deleteButton;
    private ArrayList<String> tasks;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEntry = findViewById(R.id.task_entry);
        addButton = findViewById(R.id.add_button);
        taskListView = findViewById(R.id.task_listview);
        deleteButton = findViewById(R.id.delete_button);

        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask();
            }
        });

        // Enable ListView item selection
        taskListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void addTask() {
        String task = taskEntry.getText().toString().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            adapter.notifyDataSetChanged();
            taskEntry.setText("");
        } else {
            Toast.makeText(this, "Please enter a task.", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteTask() {
        int selectedItemPosition = taskListView.getCheckedItemPosition();
        if (selectedItemPosition != ListView.INVALID_POSITION) {
            tasks.remove(selectedItemPosition);
            adapter.notifyDataSetChanged();
            // Clear selection after deletion
            taskListView.clearChoices();
        } else {
            Toast.makeText(this, "Please select a task to delete.", Toast.LENGTH_SHORT).show();
        }
    }
}
