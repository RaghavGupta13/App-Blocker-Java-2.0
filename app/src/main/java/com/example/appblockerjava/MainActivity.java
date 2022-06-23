package com.example.appblockerjava;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private App_list_adapter adapter;
    private List<ModelClass> list;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.id_fab);
        recyclerView = findViewById(R.id.id_recycler_view);

        getIntentList();
        showAllApps();


    }

    private void showAllApps() {
        //handles the click event for the floating action button
        //will open a new activity which will contain the list of all the apps running on the system

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllAppsActivity.class);
                //receiveBlockedAppsList.launch(intent);
                startActivity(new Intent(MainActivity.this, AllAppsActivity.class));
            }
        });


    }

    //Setting up the adapter
    public void setAdapter(List<ModelClass> list) {
        adapter = new App_list_adapter(list, R.layout.single_item_layout_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(adapter);

    }

    //Getting and displaying the blocked apps list that user selected from all apps list
    private void getIntentList() {

        btn = findViewById(R.id.id_show_blocked_apps_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.this.getIntent().getParcelableArrayListExtra("blockedAppsList") != null) {
                    list = new ArrayList<ModelClass>(MainActivity.this.getIntent().getParcelableArrayListExtra("blockedAppsList"));
                    Log.d(TAG, "getIntentList: " + list.get(0).getApp_name());
                    setAdapter(list);
                }
            }
        });


    }

    //Method to detect blocked apps and display a Lock Activity if matched
    /*
    This method will be implemented from the ** PROTOTYPE APP ** where we will search for
    the top package name in the list that we got from 'getIntentList()' method.
    If there is a match, the Lock Activity will take over
     */
    private void startBlockingApps(){}
}