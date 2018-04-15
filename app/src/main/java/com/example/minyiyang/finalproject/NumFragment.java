package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class NumFragment extends Fragment {
    Bundle getInfo;
    TextView NumQust;
    TextView Numans;
    Button del_b;
    Button update_b;
    Boolean isTablet;
    Long id;
    String quest;
    String ans;

    private SQLiteDatabase writableDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInfo = getArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View gui = inflater.inflate(R.layout.fragment_num, null);

        NumQust = (TextView) gui.findViewById(R.id.NumQust);

        Numans = (TextView) gui.findViewById(R.id.Numans);

        del_b = (Button) gui.findViewById(R.id.del_b);
        update_b = (Button) gui.findViewById(R.id.update_b);

        isTablet = getInfo.getBoolean("isTablet");
        id = getInfo.getLong("id");
        quest = getInfo.getString("NumCon");
        ans = getInfo.getString("NumAns");

        NumQust.setText(quest);
        Numans.setText(ans);

        del_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTablet) {
                    DatabaseHelper mydb = new DatabaseHelper(getActivity());
                    writableDB = mydb.getWritableDatabase();
                    writableDB.delete(mydb.Num_TABLE, mydb.Num_ID + "=" + id, null);
                    getActivity().finish();
                    Intent intent = getActivity().getIntent();
                    startActivity(intent);
                }else{
                    Intent intent = new Intent();
                    intent.putExtra("id", id);
                    getActivity().setResult(12, intent);
                    getActivity().finish();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),EditeNumQuest.class);
                intent.putExtra("id", id);
                intent.putExtra("quest", quest);
                intent.putExtra("ans", ans);

                startActivity(intent);
            }
        });

        return gui;
    }
}
