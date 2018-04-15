package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class EditeMultiQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeMultiQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    Cursor cursor;
    private EditText input_quest;
    private EditText input_answer1;
    private EditText input_answer2;
    private EditText input_answer3;
    private EditText input_answer4;
    private EditText cor_answer;
    private Long id;
    private RelativeLayout mutil_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_multi_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        mutil_view = (RelativeLayout) findViewById(R.id.mutil_view);
        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        input_quest= (EditText) findViewById(R.id.input_quest);
        input_answer1= (EditText) findViewById(R.id.input_answer1);
        input_answer2= (EditText) findViewById(R.id.input_answer2);
        input_answer3= (EditText) findViewById(R.id.input_answer3);
        input_answer4= (EditText) findViewById(R.id.input_answer4);
        cor_answer= (EditText) findViewById(R.id.cor_answer);

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //insert data
                    String quest = input_quest.getText().toString().trim();
                    String ans1 = input_answer1.getText().toString().trim();
                    String ans2 = input_answer2.getText().toString().trim();
                    String ans3 = input_answer3.getText().toString().trim();
                    String ans4 = input_answer4.getText().toString().trim();
                    String corans = cor_answer.getText().toString().trim();

                    if( quest.equals("")){
                        input_quest.setError("Cannot be null");
                    }
                    if( ans1.equals("")|| ans1.equals(ans2) || ans1.equals(ans3) || ans1.equals(ans4) ){
                        input_answer1.setError("Cannot be null or same content with others");
                    }
                    if(ans2.equals("") || ans2.equals(ans1) || ans2.equals(ans3) || ans2.equals(ans4)){
                        input_answer2.setError("Cannot be null or same content with others");
                    }
                    if(ans3.equals("") || ans3.equals(ans1) || ans3.equals(ans2) || ans3.equals(ans4)){
                        input_answer3.setError("Cannot be null or same content with others");
                    }
                    if(ans4.equals("") || ans4.equals(ans1) || ans4.equals(ans2) || ans4.equals(ans3)){
                        input_answer4.setError("Cannot be null or same content with others");
                    }
                    if (corans.equals("") || (!corans.equals(ans1) && !corans.equals(ans2) && !corans.equals(ans3) && !corans.equals(ans4) )){
                        cor_answer.setError("Invalid answer");
                    }else {
                        ContentValues cv = new ContentValues();
                        cv.put(mydb.Mtil_QUEST, quest);
                        cv.put(mydb.Mtil_ANS1, ans1);
                        cv.put(mydb.Mtil_ANS2, ans2);
                        cv.put(mydb.Mtil_ANS3, ans3);
                        cv.put(mydb.Mtil_ANS4, ans4);
                        cv.put(mydb.Mtil_CORRT, corans);

                        if (id == 1) {
                            db.insert(mydb.Mtil_TABLE, null, cv);

                            Toast toast = Toast.makeText(EditeMultiQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                            toast.show(); //display box
                        } else {
                            db.update(mydb.Mtil_TABLE, cv, "Mtil_ID =" + id, null);

                            Toast toast = Toast.makeText(EditeMultiQuest.this, "update successful", Toast.LENGTH_SHORT); //this is the ListActivity
                            toast.show(); //display box
                        }
                        //reset data
                        input_quest.setText("");
                        input_answer1.setText("");
                        input_answer2.setText("");
                        input_answer3.setText("");
                        input_answer4.setText("");
                        cor_answer.setText("");

                        Intent intent = new Intent(EditeMultiQuest.this, ListQuestions.class);
                        startActivity(intent);
                    }
                }
        });

        cancle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditeMultiQuest.this.finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        String quest = getIntent().getStringExtra("quest");
        String ans1 = getIntent().getStringExtra("ans1");
        String ans2 = getIntent().getStringExtra("ans2");
        String ans3 = getIntent().getStringExtra("ans3");
        String ans4 = getIntent().getStringExtra("ans4");
        String croans = getIntent().getStringExtra("croans");
        id = getIntent().getLongExtra("id",1);

        input_quest.setText(quest);
        input_answer1.setText(ans1);
        input_answer2.setText(ans2);
        input_answer3.setText(ans3);
        input_answer4.setText(ans4);
        cor_answer.setText(croans);
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
