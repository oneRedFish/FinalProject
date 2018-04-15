package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

public class ListQuestions extends Activity {
    protected static final String ACTIVITY_NAME = "ListQuestions";
    private ListView MultiQuest;
    private ListView NumQuest;
    private ListView TFQuest;
    private Button add;
    private ProgressBar pb;
    private FrameLayout fl;
    public DatabaseHelper mydb;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Cursor cursor1;
    private Cursor cursor2;
    private Bundle infoToPass;

    MultiAdapter multiAdapter;

    ArrayList<String> content = new ArrayList<>();
    ArrayList<String> content1 = new ArrayList<>();
    ArrayList<String> content2 = new ArrayList<>();

    private boolean isTablet;
    private String type="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);
        NumQuest = (ListView) findViewById(R.id.NumQuesView);
        TFQuest = (ListView) findViewById(R.id.TFQuesView);
        MultiQuest = (ListView) findViewById(R.id.MultiQuesView);
        infoToPass = new Bundle();

        add = (Button) findViewById(R.id.add);
        pb=(ProgressBar) findViewById(R.id.pb);
        fl = (FrameLayout) findViewById(R.id.fl);
        isTablet = (fl != null);

        //initialize ChatDatabaseHelper
        mydb = new DatabaseHelper(this);
        db = mydb.getWritableDatabase();
        Log.i("FrameLayout", "avaliable: " + isTablet);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set dialog
                final View conView = getLayoutInflater().inflate(R.layout.dialog_choose, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(ListQuestions.this);
                builder.setView(conView);
                Button addMultiQuest = (Button)conView.findViewById(R.id.addMultiQuest);
                Button addNumQuest = (Button)conView.findViewById(R.id.addNumQuest);
                Button addTFQuest = (Button)conView.findViewById(R.id.addTFQuest);

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

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
        //show data
        showData();
        action();

    }

    public void showData() {
        //table1
        cursor = db.query(false, mydb.Mtil_TABLE, new String[]{mydb.Mtil_ID, mydb.Mtil_QUEST,mydb.Mtil_ANS1,mydb.Mtil_ANS2,mydb.Mtil_ANS3,mydb.Mtil_ANS4,mydb.Mtil_CORRT}, null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int n=0;
            content.add(cursor.getString(cursor.getColumnIndex(mydb.Mtil_QUEST)));
            cursor.moveToNext();
            n++;
        }
        multiAdapter=new MultiAdapter(this,R.layout.multi_row,R.id.MultiQuesView,content);
        MultiQuest.setAdapter(multiAdapter);
        multiAdapter.notifyDataSetChanged();

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content);

        //table2
        cursor1 = db.query(false, mydb.TF_TABLE, new String[]{mydb.TF_ID, mydb.TF_QUEST,mydb.TF_ANS}, null, null, null, null, null, null);
        cursor1.moveToFirst();
        while (!cursor1.isAfterLast()) {
            int i=0;
            content1.add( cursor1.getString(cursor1.getColumnIndex(mydb.TF_QUEST)));
            cursor1.moveToNext();
            i++;
        }
        TFAdapter tfAdapter =new TFAdapter(this,R.layout.tf_row,R.id.TFQuesView,content1);
        TFQuest.setAdapter(tfAdapter);

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content1);

        //table3
        cursor2 = db.query(false, mydb.Num_TABLE, new String[]{mydb.Num_ID, mydb.Num_QUEST,mydb.Num_ANS}, null, null, null, null, null, null);
        cursor2.moveToFirst();
        while (!cursor2.isAfterLast()) {
            int i=0;
            content2.add(cursor2.getString(cursor2.getColumnIndex(mydb.Num_QUEST)));
            cursor2.moveToNext();
            i++;
        }
        NumAdapter numAdapter =new NumAdapter(this,R.layout.tf_row,R.id.TFQuesView,content2);
        NumQuest.setAdapter(numAdapter);

        Log.i(ACTIVITY_NAME, "Cursor’s con =" + content2);
    }
    public ArrayAdapter getAdapter(){
          return multiAdapter;
    }
    //set item action
    public void action(){
        //click list items
        MultiQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor.moveToPosition(position);
                id = cursor.getLong(cursor.getColumnIndex(mydb.Mtil_ID));
                String quest = cursor.getString(cursor.getColumnIndex(mydb.Mtil_QUEST));
                String ans1 = cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS1));
                String ans2 = cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS2));
                String ans3 = cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS3));
                String ans4 = cursor.getString(cursor.getColumnIndex(mydb.Mtil_ANS4));
                String corans = cursor.getString(cursor.getColumnIndex(mydb.Mtil_CORRT));
                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("QustCon", quest);
                infoToPass.putString("textCon1", ans1);
                infoToPass.putString("textCon2", ans2);
                infoToPass.putString("textCon3", ans3);
                infoToPass.putString("textCon4", ans4);
                infoToPass.putString("corans", corans);
                infoToPass.putLong("id", id);
                type = "isMulti";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    MultiFragment df = new MultiFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 3);
                }
            }
        });

        //click list items
        NumQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor2.moveToPosition(position);
                id = cursor2.getLong(cursor2.getColumnIndex(mydb.Num_ID));
                String quest = cursor2.getString(cursor2.getColumnIndex(mydb.Num_QUEST));
                String ans = cursor2.getString(cursor2.getColumnIndex(mydb.Num_ANS));

                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("NumCon", quest);
                infoToPass.putString("NumAns", ans);
                infoToPass.putLong("id", id);
                type = "isNum";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    NumFragment df = new NumFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 4);
                }
            }
        });

        //click list items
        TFQuest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String note = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ListQuestions.this, note, Toast.LENGTH_LONG).show();
                cursor1.moveToPosition(position);
                id = cursor1.getLong(cursor1.getColumnIndex(mydb.TF_ID));
                String quest = cursor1.getString(cursor1.getColumnIndex(mydb.TF_QUEST));
                String ans = cursor1.getString(cursor1.getColumnIndex(mydb.TF_ANS));

                Log.i(ACTIVITY_NAME, "Cursor’s column id =" + id);
                //put data into bundle
                infoToPass.putString("TFCon", quest);
                infoToPass.putString("TFAns", ans);
                infoToPass.putLong("id", id);
                type = "isTF";
                infoToPass.putString("type", type);
                infoToPass.putBoolean("isTablet", isTablet);
                //if on tablet:
                if (isTablet) {
                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    TFFragment df = new TFFragment();
                    df.setArguments(infoToPass);
                    ft.addToBackStack(null); //only undo FT on back button
                    ft.replace(R.id.fl, df);
                    ft.commit();
                }
                else //this is a phone:
                {
                    Intent next = new Intent(ListQuestions.this, ShowQuest.class);
                    next.putExtras(infoToPass);
                    startActivityForResult(next, 5);
                }
            }
        });
    }
    //delete data on phone and renew list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 11) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.Mtil_TABLE, mydb.Mtil_ID + "=" + id, null);
            Log.i("Mtil_TABLE", id + " is deleted");
        }

        if (resultCode == 12) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.Num_TABLE, mydb.Num_ID + "=" + id, null);
            Log.i("Num_TABLE", id + " is deleted");
        }

        if (resultCode == 13) {
            Bundle basket = data.getExtras();
            Long id = basket.getLong("id");
            db.delete(mydb.TF_TABLE, mydb.TF_ID + "=" + id, null);
            Log.i("TF_TABLE", id + " is deleted");
        }
        //renew list
        content.clear();
        content1.clear();
        content2.clear();
        showData();
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
