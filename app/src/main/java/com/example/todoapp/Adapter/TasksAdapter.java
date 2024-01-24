package com.example.todoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.Model.ToDoModel;
import com.example.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ViewHolder> {

    private List<ToDoModel> tasksList;
    private MainActivity activity;

    public TasksAdapter(MainActivity a)
    {
        activity=a;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoModel item= tasksList.get(position);
        holder.statusOfTask.setText(item.getTask());

        holder.statusOfTask.setChecked(item.getStatus()!=0);
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox statusOfTask;
        ViewHolder(View view){
            super(view);
            statusOfTask=view.findViewById(R.id.checkbox);
        }
    }

    public void setTasksList(List<ToDoModel> list){
        tasksList=new ArrayList<>();
        tasksList=list;
        notifyDataSetChanged();
    }
}
