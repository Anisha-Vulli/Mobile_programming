package com.example.android.todolist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

// (1) make this class extend AndroidViewModel and implement its default constructor
public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<TaskEntry>> tasks;

    // (2) Add a tasks member variable for a list of TaskEntry objects wrapped in a LiveData
    public MainViewModel(Application application) {
        super(application);
        // (4) In the constructor use the loadAllTasks of the taskDao to initialize the tasks variable
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(TAG, "Actively retrieving the tasks from the Database");
        tasks = database.taskDao().loadAllTasks();
    }




    // (3) Create a getter for the tasks variable

    public LiveData<List<TaskEntry>> getTasks() {
        return tasks;
    }
}
