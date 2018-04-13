package com.example.minyiyang.finalproject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowDetail extends Activity {
    protected static final String ACTIVITY_NAME = "ShowDetail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

    }

//    private void loadFragment(Fragment fragment) {
//        // create a FragmentManager
//        FragmentManager fm = getFragmentManager();
//        // create a FragmentTransaction to begin the transaction and replace the Fragment
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        // replace the FrameLayout with new Fragment
//        fragmentTransaction.replace(R.id.fl_d, fragment);
//        fragmentTransaction.commit(); // save the changes
//    }

}
