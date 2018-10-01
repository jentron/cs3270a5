package com.jentronics.cs3270a5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_set_max:
                action_set_max();
                return true;
            case R.id.action_zero_score:
                action_zero_score();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void action_set_max() {
        Toast.makeText(this, R.string.option_set_max_title, Toast.LENGTH_SHORT).show();
    }
    private void action_zero_score() {
        Toast.makeText(this, R.string.option_zero_score_title, Toast.LENGTH_SHORT).show();
    }
}
