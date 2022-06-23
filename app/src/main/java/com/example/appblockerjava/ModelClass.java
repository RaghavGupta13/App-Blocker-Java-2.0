package com.example.appblockerjava;

import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

//import org.parceler.Parcel;

public class ModelClass implements Parcelable{

    private String app_name;
    private String app_package;

    public ModelClass(String app_name, String app_package){
        this.app_name = app_name;
        this.app_package = app_package;
    }


    protected ModelClass(Parcel in) {
        app_name = in.readString();
        app_package = in.readString();
    }

    public static final Creator<ModelClass> CREATOR = new Creator<ModelClass>() {
        @Override
        public ModelClass createFromParcel(Parcel in) {
            return new ModelClass(in);
        }

        @Override
        public ModelClass[] newArray(int size) {
            return new ModelClass[size];
        }
    };

    public String getApp_name() {
        return app_name;
    }


    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }


    public String getApp_package() {
        return app_package;
    }

    public void setApp_package(String app_package) {
        this.app_package = app_package;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(app_name);
        parcel.writeString(app_package);
    }
}
