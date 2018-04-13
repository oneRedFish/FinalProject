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
import android.widget.TextView;
import android.widget.Toast;


public class EditeMultiQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeMultiQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_multi_quest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
            EditText input_quest= (EditText) findViewById(R.id.input_quest);
            EditText input_answer1= (EditText) findViewById(R.id.input_answer1);
            EditText input_answer2= (EditText) findViewById(R.id.input_answer2);
            EditText input_answer3= (EditText) findViewById(R.id.input_answer3);
            EditText input_answer4= (EditText) findViewById(R.id.input_answer4);
            EditText cor_answer= (EditText) findViewById(R.id.cor_answer);
            @Override
            public void onClick(View v) {
                //insert data
                ContentValues cv = new ContentValues();
                cv.put(mydb.Mtil_QUEST, input_quest.getText().toString());
                cv.put(mydb.Mtil_ANS1, input_answer1.getText().toString());
                cv.put(mydb.Mtil_ANS2, input_answer2.getText().toString());
                cv.put(mydb.Mtil_ANS3, input_answer3.getText().toString());
                cv.put(mydb.Mtil_ANS4, input_answer4.getText().toString());
                cv.put(mydb.Mtil_CORRT, cor_answer.getText().toString());

                db.insert(mydb.Mtil_TABLE, null, cv);

                Toast toast = Toast.makeText(EditeMultiQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                toast.show(); //display box
                //reset data
                input_quest.setText("");
                input_answer1.setText("");
                input_answer2.setText("");
                input_answer3.setText("");
                input_answer4.setText("");
                cor_answer.setText("");

                Intent intent = new Intent(EditeMultiQuest.this, MainActivity.class);
                startActivityForResult(intent, 50);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText input_quest= (EditText) findViewById(R.id.input_quest);
        EditText input_answer1= (EditText) findViewById(R.id.input_answer1);
        EditText input_answer2= (EditText) findViewById(R.id.input_answer2);
        EditText input_answer3= (EditText) findViewById(R.id.input_answer3);
        EditText input_answer4= (EditText) findViewById(R.id.input_answer4);
        EditText cor_answer= (EditText) findViewById(R.id.cor_answer);
        if (resultCode == RESULT_OK && requestCode == 4) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            cursor = db.rawQuery("Select * from "+mydb.Mtil_TABLE+" where "+ mydb.Mtil_ID + "=" + id, null);

            input_quest.setText(cursor.getString(cursor.getColumnIndex(mydb.Mtil_QUEST)));
            input_answer1.setText(cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS1)));
            input_answer2.setText(cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS2)));
            input_answer3.setText(cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS3)));
            input_answer4.setText(cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS4)));

        }
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
