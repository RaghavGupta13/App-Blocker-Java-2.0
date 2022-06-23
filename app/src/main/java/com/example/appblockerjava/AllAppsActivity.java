package com.example.appblockerjava;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AllAppsActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private App_list_adapter adapter;
    private ModelClass modelClass;
    private ArrayList<ModelClass> blockedApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apps);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setHomeButtonEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.id_recycler_view_all_apps);
        getAppNames();
        setAdapter();
    }

    //Method to get names of all the apps on the system
    private void getAppNames(){
        //List<ApplicationInfo> packageInfos = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
        List<ModelClass> list = new ArrayList<>();

        for(int i=0; i<resolveInfos.size(); i++){
            String app_name = resolveInfos.get(i).activityInfo.loadLabel(getPackageManager()).toString();
            String app_package = resolveInfos.get(i).activityInfo.packageName;
            //Drawable app_icon = resolveInfos.get(i).activityInfo.loadIcon(getPackageManager());

            modelClass = new ModelClass(app_name, app_package);
            list.add(modelClass);

        }
        adapter = new App_list_adapter(list, R.layout.single_item_layout);
    }

    // Setup recycler view and adapter
    private void setAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(AllAppsActivity.this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.id_menu_ok){
            blockedApps = new ArrayList<>(adapter.getBlockedAppsList());

            Intent intent = new Intent(AllAppsActivity.this, MainActivity.class);
            intent.putParcelableArrayListExtra("blockedAppsList", blockedApps);
            startActivity(intent);
            finish();
        }

        return true;
    }
}