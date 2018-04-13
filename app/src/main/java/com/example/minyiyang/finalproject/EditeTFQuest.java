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
import android.widget.RadioButton;
import android.widget.Toast;

public class EditeTFQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeTFQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_tfquest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
            EditText TF_quest= (EditText) findViewById(R.id.TF_quest);
            RadioButton RB1= (RadioButton) findViewById(R.id.rbt1);
            RadioButton RB2= (RadioButton) findViewById(R.id.rbt2);


            EditText cor_answer= (EditText) findViewById(R.id.cor_answer);
            @Override
            public void onClick(View v) {
                //insert data
                ContentValues cv = new ContentValues();
                cv.put(mydb.TF_QUEST, TF_quest.getText().toString());
                if(RB1.isChecked())
                    cv.put(mydb.TF_ANS, "true");
                if(RB2.isChecked())
                    cv.put(mydb.TF_ANS, "false");


                db.insert(mydb.TF_TABLE, null, cv);

                Toast toast = Toast.makeText(EditeTFQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                toast.show(); //display box
                //reset data
                TF_quest.setText("");
                RB1.setText("");
                RB2.setText("");

                Intent intent = new Intent(EditeTFQuest.this, MainActivity.class);
                startActivityForResult(intent, 60);
            }
        });

        cancle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditeTFQuest.this.finish();
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
