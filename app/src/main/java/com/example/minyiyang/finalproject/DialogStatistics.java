package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class DialogStatistics extends Activity {
    protected static final String ACTIVITY_NAME = "DialogStatistics";
    private TextView count;
    private TextView long_ans;
    private TextView short_ans;
    private TextView ave_ans;
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Cursor cursor1;
    private Cursor cursor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_statistics);

        ArrayList<String> content = new ArrayList<>();
        ArrayList<String> content1 = new ArrayList<>();
        ArrayList<String> content2 = new ArrayList<>();

        count = (TextView) findViewById(R.id.count);
        long_ans = (TextView) findViewById(R.id.long_ans);
        short_ans = (TextView) findViewById(R.id.short_ans);
        ave_ans = (TextView) findViewById(R.id.ave_ans);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();

        //table1
        cursor = db.query(false, mydb.Mtil_TABLE, new String[]{mydb.Mtil_ID, mydb.Mtil_QUEST,mydb.Mtil_ANS1,mydb.Mtil_ANS2,mydb.Mtil_ANS3,mydb.Mtil_ANS4,mydb.Mtil_CORRT}, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            content.add(cursor.getString(cursor.getColumnIndex(mydb.Mtil_QUEST)));
            cursor.moveToNext();
        }

        //table2
        cursor1 = db.query(false, mydb.TF_TABLE, new String[]{mydb.TF_ID, mydb.TF_QUEST,mydb.TF_ANS}, null, null, null, null, null, null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            content1.add( cursor1.getString(cursor1.getColumnIndex(mydb.TF_QUEST)));
            cursor1.moveToNext();
        }

        //table3
        cursor2 = db.query(false, mydb.Num_TABLE, new String[]{mydb.Num_ID, mydb.Num_QUEST,mydb.Num_ANS}, null, null, null, null, null, null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            content2.add(cursor2.getString(cursor2.getColumnIndex(mydb.Num_QUEST)));
            cursor2.moveToNext();
        }
        int amount = cursor.getCount()+cursor1.getCount()+cursor2.getCount();
        count.setText(Integer.toString(amount));

        //get longest
        int LongLength = 1;
        int totalLength = 0;
        for(int i=0; i<content.size(); i++){
            if(content.get(i).length()>LongLength )
                LongLength=content.get(i).length();
            totalLength += content.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "length =" + LongLength);

        int LongLength1 = 1;
        for(int i=0; i<content1.size(); i++){
            if(content1.get(i).length()>LongLength1 )
                LongLength1=content1.get(i).length();
            totalLength += content1.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "length1 =" + LongLength1);

        int LongLength2 = 1;
        for(int i=0; i<content2.size(); i++){
            if(content2.get(i).length()>LongLength2 )
                LongLength2=content2.get(i).length();
            totalLength += content2.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "length2 =" + LongLength2);

        int Longest = (LongLength > LongLength1)? LongLength : LongLength1;
        Longest = (Longest > LongLength2) ? Longest : LongLength2;
        long_ans.setText(Integer.toString(Longest));

        //get shortest
        int ShortLength = Integer.MAX_VALUE;
        for(int i=0; i<content.size(); i++){
            if(content.get(i).length()<ShortLength )
                ShortLength=content.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "Slength =" + ShortLength);

        int ShortLength1 = Integer.MAX_VALUE;;
        for(int i=0; i<content1.size(); i++){
            if(content1.get(i).length()<ShortLength1 )
                ShortLength1=content1.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "Slength1 =" + ShortLength1);

        int ShortLength2 = Integer.MAX_VALUE;;
        for(int i=0; i<content2.size(); i++){
            if(content2.get(i).length()<ShortLength2 )
                ShortLength2=content2.get(i).length();
        }
        Log.i(ACTIVITY_NAME, "Slength2 =" + ShortLength2);

        int Shortest = (ShortLength < ShortLength1)? ShortLength : ShortLength1;
        Shortest = (Shortest < ShortLength2)? Shortest : ShortLength2;

        short_ans.setText(Integer.toString(Shortest));

        ave_ans.setText(Integer.toString(totalLength/amount));

    }
}
