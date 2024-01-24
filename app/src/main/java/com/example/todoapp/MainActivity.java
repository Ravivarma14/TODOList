package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todoapp.Adapter.TasksAdapter;
import com.example.todoapp.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private TasksAdapter tasksAdapter;
    private List<ToDoModel> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tasksRecyclerView=findViewById(R.id.recycler_view);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter=new TasksAdapter(this);
        tasksRecyclerView.setAdapter(tasksAdapter);

        tasks=new ArrayList<>();

        ToDoModel task=new ToDoModel();
        task.setTask("This is a test Task");
        task.setStatus(0);
        task.setId(1);

        ToDoModel task1=new ToDoModel();
        task1.setTask("This is a test Task");
        task1.setStatus(1);
        task1.setId(1);


        tasks.add(task);
        tasks.add(task1);
        tasks.add(task);
        tasks.add(task1);
        tasks.add(task);
        tasks.add(task1);


        tasksAdapter.setTasksList(tasks);


    }
}