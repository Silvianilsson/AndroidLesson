package com.mycompany.myapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import se.softwerk.helloandroid.R;

public class MyFirstApp extends Activity {

    private Button sendBtn;
    private TextView textView;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my_first_app, menu);
        return true;
    }

    
}
