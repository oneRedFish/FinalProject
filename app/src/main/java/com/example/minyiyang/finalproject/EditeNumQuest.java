package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditeNumQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeNumQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_num_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
            EditText Num_quest= (EditText) findViewById(R.id.Num_quest);
            EditText Num_answer= (EditText) findViewById(R.id.Num_answer);

            EditText cor_answer= (EditText) findViewById(R.id.cor_answer);
            @Override
            public void onClick(View v) {
                //insert data
                ContentValues cv = new ContentValues();
                cv.put(mydb.Num_QUEST, Num_quest.getText().toString());
                cv.put(mydb.Num_ANS, Num_answer.getText().toString());

                db.insert(mydb.Num_TABLE, null, cv);

                Toast toast = Toast.makeText(EditeNumQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                toast.show(); //display box
                //reset data
                Num_quest.setText("");
                Num_answer.setText("");

                Intent intent = new Intent(EditeNumQuest.this, MainActivity.class);
                startActivityForResult(intent, 60);
            }
        });

        cancle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditeNumQuest.this.finish();
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
