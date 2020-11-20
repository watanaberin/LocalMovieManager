package com.example.application;

import android.app.Application;

public class UserApplication extends Application {
    private static String name="123";
    @Override
    public void onCreate() {
        super.onCreate();
        setName(name);

    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
}
