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

import java.security.PrivateKey;
import java.util.regex.Pattern;

public class EditeTFQuest extends Activity {
    protected static final String ACTIVITY_NAME = "EditeTFQuest";
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private EditText TF_quest;
    private RadioButton RB1;
    private RadioButton RB2;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edite_tfquest);

        Button submit_button = (Button) findViewById(R.id.submit_button);
        Button cancle_button = (Button) findViewById(R.id.cancle_button);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        TF_quest= (EditText) findViewById(R.id.TF_quest);
        RB1= (RadioButton) findViewById(R.id.rbt1);
        RB2= (RadioButton) findViewById(R.id.rbt2);
        //save value to arraylist
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quest= TF_quest.getText().toString().trim();
                if( quest.equals("")){
                    TF_quest.setError("Cannot be null");
                }
                else if(!RB1.isChecked() && !RB2.isChecked()) {
                    RB2.setError("please select one");
                }else {
                    //insert data
                    ContentValues cv = new ContentValues();
                    cv.put(mydb.TF_QUEST, TF_quest.getText().toString());
                    if (RB1.isChecked())
                        cv.put(mydb.TF_ANS, "true");
                    if (RB2.isChecked())
                        cv.put(mydb.TF_ANS, "false");

                    if (id == 1) {
                        db.insert(mydb.TF_TABLE, null, cv);

                        Toast toast = Toast.makeText(EditeTFQuest.this, "insert successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    } else {
                        db.update(mydb.TF_TABLE, cv, "TF_ID =" + id, null);

                        Toast toast = Toast.makeText(EditeTFQuest.this, "update successful", Toast.LENGTH_SHORT); //this is the ListActivity
                        toast.show(); //display box
                    }
                    //reset data
                    TF_quest.setText("");
                    RB1.setText("");
                    RB2.setText("");

                    Intent intent = new Intent(EditeTFQuest.this, ListQuestions.class);
                    startActivityForResult(intent, 60);
                }
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
        String quest = getIntent().getStringExtra("quest");
        String ans = getIntent().getStringExtra("ans");

        id = getIntent().getLongExtra("id",1);

        TF_quest.setText(quest);
        if(ans!=null) {
            if (ans.equals("true")) {
                RB1.setChecked(true);
            } else {
                RB2.setChecked(true);
            }
        }
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
