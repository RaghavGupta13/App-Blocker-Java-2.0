package com.example.appblockerjava;

import static android.content.ContentValues.TAG;

import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//Adapter Class for the Recycler view
public class App_list_adapter extends RecyclerView.Adapter<AppViewholder> {

    private List<ModelClass> list;
    private ArrayList<ModelClass> blockedApps = new ArrayList<>();
    ModelClass modelClass;
    int layout_id;

    public App_list_adapter(List<ModelClass> list, int layout_id){
        this.list=  list;
        this.layout_id = layout_id;
    }

    @NonNull
    @Override
    public AppViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout_id, parent, false);
        return new AppViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewholder holder, int position) {
        ModelClass currentApp = list.get(position);
        holder.app_name.setText(currentApp.getApp_name());
        //holder.app_icon.setImageDrawable(currentApp.getApp_icon());
        holder.app_package.setText(currentApp.getApp_package());

        if(holder.checkBox != null){
            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.checkBox.isChecked()){
                        modelClass = new ModelClass(currentApp.getApp_name(), currentApp.getApp_package());
                        blockedApps.add(modelClass);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        if(list.size() > 0 || list != null){
            return list.size();
        }else{
            return 0;
        }
    }

    public ArrayList<ModelClass> getBlockedAppsList(){
        notifyDataSetChanged();
        return blockedApps;
    }
}

