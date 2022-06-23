package com.example.appblockerjava;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//ViewHolder class for the recycler view
public class AppViewholder extends RecyclerView.ViewHolder{

    public ImageView app_icon;
    public TextView app_name, app_package;
    public CheckBox checkBox;

    public AppViewholder(@NonNull View itemView){
        super(itemView);

        app_name = itemView.findViewById(R.id.id_single_item_app_text);
        app_icon = itemView.findViewById(R.id.id_app_icon);
        checkBox = itemView.findViewById(R.id.id_checkbox);
        app_package = itemView.findViewById(R.id.id_single_item_app_package);
    }



}
