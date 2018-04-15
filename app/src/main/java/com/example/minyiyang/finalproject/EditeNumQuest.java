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

import java.util.regex.Pattern;

public class EditeNumQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeNumQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private EditText Num_quest;
    private EditText Num_answer;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_num_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        Num_quest= (EditText) findViewById(R.id.Num_quest);
        Num_answer= (EditText) findViewById(R.id.Num_answer);
        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String quest=Num_quest.getText().toString().trim();
                String answer=Num_answer.getText().toString().trim();
                if( quest.equals("")){
                    Num_quest.setError("Cannot be null");
                }
                else if(answer.equals("") || !Pattern.matches("([1-9]\\d*(\\.\\d*[1-9])?)|(0\\.\\d*[1-9])",answer)) {
                    Num_answer.setError("Cannot be null and please enter a float greater than 0");
                }
                else {
                    float num =  Float.parseFloat(answer);
                    //insert data
                    ContentValues cv = new ContentValues();
                    cv.put(mydb.Num_QUEST, quest);
                    cv.put(mydb.Num_ANS, String.format("%.2f", num));

                    if (id == 1) {
                        db.insert(mydb.Num_TABLE, null, cv);

                        Toast toast = Toast.makeText(EditeNumQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    } else {
                        db.update(mydb.Num_TABLE, cv, "Num_ID =" + id, null);

                        Toast toast = Toast.makeText(EditeNumQuest.this, "update successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    }
                    //reset data
                    Num_quest.setText("");
                    Num_answer.setText("");

                    Intent intent = new Intent(EditeNumQuest.this, ListQuestions.class);
                    startActivityForResult(intent, 60);
                }
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
        String quest = getIntent().getStringExtra("quest");
        String ans = getIntent().getStringExtra("ans");

        id = getIntent().getLongExtra("id",1);

        Num_quest.setText(quest);
        Num_answer.setText(ans);

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
