package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MultiFragment extends Fragment {
    TextView QustCon;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    Bundle getInfo;
    TextView textCon1;
    TextView textCon2;
    TextView textCon3;
    TextView textCon4;
    Button del_b;
    Button update_b;
    Boolean isTablet;
    Long id;
    String quest;
    String ans1;
    String ans2;
    String ans3;
    String ans4;
    String croans;
    private SQLiteDatabase writableDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInfo = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.fragment_multi, null);

        QustCon = (TextView) gui.findViewById(R.id.QustCon);
        rb1 = (RadioButton) gui.findViewById(R.id.rb1);
        rb2 = (RadioButton) gui.findViewById(R.id.rb2);
        rb3 = (RadioButton) gui.findViewById(R.id.rb3);
        rb4 = (RadioButton) gui.findViewById(R.id.rb4);

        textCon1 = (TextView) gui.findViewById(R.id.textCon1);
        textCon2 = (TextView) gui.findViewById(R.id.textCon2);
        textCon3 = (TextView) gui.findViewById(R.id.textCon3);
        textCon4 = (TextView) gui.findViewById(R.id.textCon4);

        del_b = (Button) gui.findViewById(R.id.del_b);
        update_b = (Button) gui.findViewById(R.id.update_b);

        isTablet = getInfo.getBoolean("isTablet");
        id = getInfo.getLong("id");
        quest = getInfo.getString("QustCon");
        ans1 = getInfo.getString("textCon1");
        ans2 = getInfo.getString("textCon2");
        ans3 = getInfo.getString("textCon3");
        ans4 = getInfo.getString("textCon4");

        QustCon.setText(quest);
        textCon1.setText(ans1);
        textCon2.setText(ans2);
        textCon3.setText(ans3);
        textCon4.setText(ans4);
        //correct answer
        croans= getInfo.getString("corans");
        //set RadioButton
        if(croans.equals(ans1))
            rb1.setChecked(true);
        else if(croans.equals(ans2))
            rb2.setChecked(true);
        else if(croans.equals(ans3))
            rb3.setChecked(true);
        else
            rb4.setChecked(true);

        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet) {
                    DatabaseHelper mydb = new DatabaseHelper(getActivity());
                    writableDB = mydb.getWritableDatabase();
                    writableDB.delete(mydb.Mtil_TABLE, mydb.Mtil_ID + "=" + id, null);
                    getActivity().finish();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    getActivity().setResult(11, intent);
                    getActivity().finish();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditeMultiQuest.class);
                intent.putExtra("id", id);
                intent.putExtra("quest", quest);
                intent.putExtra("ans1", ans1);
                intent.putExtra("ans2", ans2);
                intent.putExtra("ans3", ans3);
                intent.putExtra("ans4", ans4);
                intent.putExtra("croans", croans);
                startActivity(intent);
            }
        });


        return gui;
    }
}
