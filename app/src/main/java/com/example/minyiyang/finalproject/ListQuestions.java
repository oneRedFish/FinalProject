package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

public class ListQuestions extends Activity {
    protected static final String ACTIVITY_NAME = "ListQuestions";
    private ListView MultiQuest;
    private ListView NumQuest;
    private ListView TFQuest;
    private Button addMultiQuest;
    private Button addNumQuest;
    private Button addTFQuest;
    private ProgressBar pb;
    private FrameLayout fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        MultiQuest = (ListView) findViewById(R.id.MultiQuesView);
        NumQuest = (ListView) findViewById(R.id.NumQuesView);
        TFQuest = (ListView) findViewById(R.id.TFQuesView);
        addMultiQuest = (Button) findViewById(R.id.addMultiQuest);
        addNumQuest = (Button) findViewById(R.id.addNumQuest);
        addTFQuest = (Button) findViewById(R.id.addTFQuest);
        pb=(ProgressBar) findViewById(R.id.pb);
        fl = (FrameLayout) findViewById(R.id.fl);

        addMultiQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQuestions.this, EditeMultiQuest.class);
                startActivityForResult(intent, 10);
            }
        });
        addNumQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQuestions.this, EditeNumQuest.class);
                startActivityForResult(intent, 20);
            }
        });
        addTFQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListQuestions.this, EditeTFQuest.class);
                startActivityForResult(intent, 30);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy");
    }
}
