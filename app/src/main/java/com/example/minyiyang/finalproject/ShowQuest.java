package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class ShowQuest extends Activity {
    protected static final String ACTIVITY_NAME = "ShowQuest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_quest);

        Bundle infoToPass = getIntent().getExtras();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MultiFragment df = new MultiFragment();
        df.setArguments(infoToPass);
        ft.addToBackStack(null); //only undo FT on back button
        ft.replace(R.id.empty_fl, df);
        ft.commit();
        Log.i(ACTIVITY_NAME, "is finished");
    }
}
