package se.softwerk.helloandroid;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final int MAX = 10;
    public static final String POSITION = "POSITION";

    private int currentPos;
    private Button addBtn;
    private Button reduceBtn;
    private ProgressBar progressBar;
    private TextView textView;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<String>(); // do first the unmodifyable list to get an exception :)
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);

        addBtn = (Button) findViewById(R.id.add_button);
        reduceBtn = (Button) findViewById(R.id.reduce_button);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        addBtn.setOnClickListener(this);
        reduceBtn.setOnClickListener(this);
        progressBar.setMax(MAX);

        Log.i("onCreate", "Creating app");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("onStart", "Starting app");
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.clear();
        currentPos = getSharedPreferences("hello", MODE_PRIVATE).getInt(POSITION, 5);
        progressBar.setProgress(currentPos);

        for (int i = 0; i <= currentPos; i++) {
            adapter.add("Item: " + i);
        }

        Log.i("OnResume", "Loading: " + currentPos);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("onRestart", "Stopping app");
    }

    @Override
    protected void onPause() {
        super.onResume();
        getSharedPreferences("hello", MODE_PRIVATE).edit().putInt(POSITION, currentPos).commit();
        Log.i("OnPause", "Storing: " + currentPos);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("OnStop", "Stopping app");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("OnDestroy", "Leaving app");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == addBtn && currentPos < MAX) {
            progressBar.setProgress(++currentPos);
            adapter.add("Item: " + currentPos);
        } else if (view == reduceBtn && currentPos > 0) {
            adapter.remove("Item: " + currentPos);
            progressBar.setProgress(--currentPos);
        } else if (view == addBtn && currentPos >= MAX) {
            Toast.makeText(this, R.string.already_10_items, Toast.LENGTH_LONG).show();
        } else if (view == reduceBtn && currentPos <= 0) {
            Toast.makeText(this, R.string.cannot_reduce_list, Toast.LENGTH_LONG).show();
        } else {
            Log.i(MainActivity.class.getName(), "Unknown event!"); // only in log, so no reason to add to resources
        }
        String hello = getString(R.string.hello_world);
        textView.setText(hello + " " + currentPos);
    }
}
